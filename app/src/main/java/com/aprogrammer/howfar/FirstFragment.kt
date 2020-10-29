package com.aprogrammer.howfar

import android.content.Intent
import android.os.AsyncTask
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
import com.google.gson.Gson
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
    lateinit var currentPlace: String
    lateinit var locations: String
    lateinit var textField: TextInputLayout
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
        mContentView = view.findViewById(R.id.mainContent);
        textField = view.findViewById(R.id.textFieldList)
        spinner = view.findViewById(R.id.progressBar)
        spinner!!.visibility = View.GONE
        view.findViewById<Button>(R.id.button_calc_distance).setOnClickListener {
            currentPlace =
                view.findViewById<TextInputLayout>(R.id.textFieldSrc).editText!!.text.toString()
                    .trimEnd()
            locations = textField.editText!!.text.toString()
            spinner!!.visibility = View.VISIBLE
            mContentView!!.visibility = View.GONE
            getCurrentData(currentPlace, locations.lines())
        }
    }

    fun openNewActivity(bundle: Bundle) {
        val myIntent = Intent(activity, PlaceDataList::class.java)
        myIntent.putExtra("bundle", bundle)
        requireActivity().startActivity(myIntent)
    }

    inner class GetPlacesData(val view: View) :
        AsyncTask<String?, Void?, String>() {
        lateinit var currentPlace: String
        lateinit var locations: List<String>
        lateinit var places: MutableList<Place>
        lateinit var textField: TextInputLayout
        override fun onPreExecute() {
            super.onPreExecute()
            spinner!!.visibility = View.VISIBLE
            currentPlace =
                view.findViewById<TextInputLayout>(R.id.textFieldSrc).editText!!.text.toString()
                    .trimEnd()
            textField = view.findViewById(R.id.textFieldList)
            locations = textField.editText!!.text.lines()
        }

        override fun doInBackground(params: Array<String?>): String? {

            places = mutableListOf()
            val h: HttpHandler = HttpHandler()
            for (location in locations) {
                val r =
                    h.makeServiceCall("https://www.distance24.org/route.json?stops=${currentPlace}|${location}")
//                val r = URL(
//                    "https://www.distance24.org/route.json?stops=" +
//                            "${currentPlace}|${location}"
//                ).readText()
                val g = Gson()
                val p = g.fromJson(r, PlacesData::class.java)
                places.add(Place(p.stops[1].city, p.distance))
            }
            return places.size.toString()
        }

        override fun onPostExecute(totalPlaces: String) {
            var str: String = ""
            places.sortBy { it.distance }
            for (place in places) {
                str = "${str}${place.Name} ${place.distance} km\n"
            }
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
            requests.add(service.getData("$currentLocation|$location"))
        }
        Observable
            .zip(requests) {
                for (i in it) {
                    i as PlacesData
                    places.add(Place(i.stops[1].city, i.distance))
                }
                // do something with those results and emit new event
                //Any() // <-- Here we emit just new empty Object(), but you can emit anything
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
                Log.d(tag,it.localizedMessage!!)
            }
    }

}

