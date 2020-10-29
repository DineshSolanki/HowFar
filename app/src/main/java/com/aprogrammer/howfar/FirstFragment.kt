package com.aprogrammer.howfar

import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputLayout
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {
    private lateinit var currentPlace: String
    private lateinit var locations: String
    private lateinit var textField: TextInputLayout
    private var spinner: ProgressBar? = null
    private var mContentView: View? = null
    private val baseUrl = "https://www.distance24.org/"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mContentView = view.findViewById(R.id.mainContent)
        textField = view.findViewById(R.id.textFieldList)
        spinner = view.findViewById(R.id.progressBar)
        spinner!!.visibility = View.GONE
        view.findViewById<Button>(R.id.button_calc_distance).setOnClickListener {
            hideKeyboard()
            currentPlace =
                view.findViewById<TextInputLayout>(R.id.textFieldSrc).editText!!.text.toString()
                    .trimEnd()
            locations = textField.editText!!.text.toString().trim()
            spinner!!.visibility = View.VISIBLE
            mContentView!!.visibility = View.GONE
            getCurrentData(currentPlace, locations.lines())
        }
        view.findViewById<Button>(R.id.button_paste).setOnClickListener {
            val clipboard =
                requireActivity().getSystemService(Context.CLIPBOARD_SERVICE) as? ClipboardManager
            val textToPaste = clipboard?.primaryClip?.getItemAt(0)?.text
            val currentText = textField.editText!!.text.toString()
            textField.editText!!.setText("$textToPaste\n$currentText")


        }
        view.findViewById<Button>(R.id.button_clear).setOnClickListener {
            textField.editText!!.text.clear()
        }
    }

    private fun getCurrentData(
        currentLocation: String,
        locations: List<String>
    ) {

        val places: MutableList<Place> = mutableListOf()
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
        val service = retrofit.create(Distance24::class.java)

        val requests = ArrayList<Observable<*>>()
        for (location in locations) {
            if (location.isNotBlank())
                requests.add(service.getData("$currentLocation|$location"))
        }
        Observable
            .zip(requests) {
                for (i in it) {
                    i as PlacesData
                    places.add(Place(i.stops[1].city, i.distance))
                }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                mContentView!!.visibility = View.VISIBLE
                spinner!!.visibility = View.GONE
                places.sortBy { it.distance }
                val myIntent = Intent(activity, PlaceDataList::class.java)
                myIntent.putExtra("currentPlace", currentPlace)
                myIntent.putParcelableArrayListExtra("locations", ArrayList<Parcelable>(places))
                requireActivity().startActivity(myIntent)
            }) {
                Log.d(tag, it.localizedMessage!!)
            }
    }

}

