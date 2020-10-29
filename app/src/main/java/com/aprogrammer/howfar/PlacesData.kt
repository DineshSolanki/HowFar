package com.aprogrammer.howfar

import com.google.gson.annotations.SerializedName


data class PlacesData(
    @SerializedName("distance")
    val distance: Int = 0,
    @SerializedName("distances")
    val distances: List<Int> = listOf(),
    @SerializedName("stops")
    val stops: List<Stop> = listOf(),
    @SerializedName("travel")
    val travel: Travel = Travel()
)

data class Stop(
    @SerializedName("airports")
    val airports: List<Any> = listOf(),
    @SerializedName("airports_timestamp")
    val airportsTimestamp: String = "",
    @SerializedName("airports_version")
    val airportsVersion: Int = 0,
    @SerializedName("alt")
    val alt: List<Any> = listOf(),
    @SerializedName("city")
    val city: String = "",
    @SerializedName("compressed")
    val compressed: Int = 0,
    @SerializedName("countryCode")
    val countryCode: String = "",
    @SerializedName("hotels")
    val hotels: List<Hotel> = listOf(),
    @SerializedName("hotels_timestamp")
    val hotelsTimestamp: String = "",
    @SerializedName("hotels_version")
    val hotelsVersion: Int = 0,
    @SerializedName("lastUpdate")
    val lastUpdate: String = "",
    @SerializedName("latitude")
    val latitude: Double = 0.0,
    @SerializedName("localTime")
    val localTime: LocalTime = LocalTime(),
    @SerializedName("locatedBy")
    val locatedBy: String = "",
    @SerializedName("locatedBy_timestamp")
    val locatedByTimestamp: String = "",
    @SerializedName("locatedBy_version")
    val locatedByVersion: Int = 0,
    @SerializedName("longitude")
    val longitude: Double = 0.0,
    @SerializedName("nearByCities")
    val nearByCities: List<NearByCity> = listOf(),
    @SerializedName("nearByCities_timestamp")
    val nearByCitiesTimestamp: String = "",
    @SerializedName("nearByCities_version")
    val nearByCitiesVersion: Int = 0,
    @SerializedName("promotions")
    val promotions: Promotions = Promotions(),
    @SerializedName("region")
    val region: String = "",
    @SerializedName("timeZone")
    val timeZone: TimeZone = TimeZone(),
    @SerializedName("timeZone_timestamp")
    val timeZoneTimestamp: String = "",
    @SerializedName("timeZone_version")
    val timeZoneVersion: Int = 0,
    @SerializedName("translations")
    val translations: Translations = Translations(),
    @SerializedName("translations_timestamp")
    val translationsTimestamp: String = "",
    @SerializedName("translations_version")
    val translationsVersion: Int = 0,
    @SerializedName("travelGuides")
    val travelGuides: List<TravelGuide> = listOf(),
    @SerializedName("travelGuides_timestamp")
    val travelGuidesTimestamp: String = "",
    @SerializedName("travelGuides_version")
    val travelGuidesVersion: Int = 0,
    @SerializedName("type")
    val type: String = "",
    @SerializedName("wikipedia")
    val wikipedia: Wikipedia = Wikipedia(),
    @SerializedName("wikipedia_timestamp")
    val wikipediaTimestamp: String = "",
    @SerializedName("wikipedia_version")
    val wikipediaVersion: Int = 0
)

data class Travel(
    @SerializedName("airports")
    val airports: Int = 0,
    @SerializedName("destination")
    val destination: Destination = Destination(),
    @SerializedName("general")
    val general: General = General(),
    @SerializedName("origin")
    val origin: Origin = Origin(),
    @SerializedName("timeOffset")
    val timeOffset: TimeOffset = TimeOffset()
)

data class Hotel(
    @SerializedName("description")
    val description: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("nativeId")
    val nativeId: String = "",
    @SerializedName("picture")
    val picture: String = "",
    @SerializedName("provider")
    val provider: String = "",
    @SerializedName("url")
    val url: String = ""
)

data class LocalTime(
    @SerializedName("dstActive")
    val dstActive: Boolean = false,
    @SerializedName("formatted")
    val formatted: String = "",
    @SerializedName("timeZoneAbbr")
    val timeZoneAbbr: String = "",
    @SerializedName("timeZoneName")
    val timeZoneName: String = ""
)

data class NearByCity(
    @SerializedName("category")
    val category: String = "",
    @SerializedName("city")
    val city: String = "",
    @SerializedName("rank")
    val rank: Int = 0
)

data class Promotions(
    @SerializedName("lastminutede")
    val lastminutede: Lastminutede = Lastminutede(),
    @SerializedName("lastminutede_timestamp")
    val lastminutedeTimestamp: String = "",
    @SerializedName("lastminutede_version")
    val lastminutedeVersion: Int = 0
)

data class TimeZone(
    @SerializedName("abbr")
    val abbr: String = "",
    @SerializedName("dstSavingsMins")
    val dstSavingsMins: Int = 0,
    @SerializedName("id")
    val id: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("offsetMin")
    val offsetMin: Int = 0
)

data class Translations(
    @SerializedName("ar")
    val ar: String = "",
    @SerializedName("da")
    val da: String = "",
    @SerializedName("de")
    val de: String = "",
    @SerializedName("el")
    val el: String = "",
    @SerializedName("en")
    val en: String = "",
    @SerializedName("es")
    val es: String = "",
    @SerializedName("fi")
    val fi: String = "",
    @SerializedName("fr")
    val fr: String = "",
    @SerializedName("it")
    val it: String = "",
    @SerializedName("ja")
    val ja: String = "",
    @SerializedName("ko")
    val ko: String = "",
    @SerializedName("nl")
    val nl: String = "",
    @SerializedName("no")
    val no: String = "",
    @SerializedName("pl")
    val pl: String = "",
    @SerializedName("pt")
    val pt: String = "",
    @SerializedName("ru")
    val ru: String = "",
    @SerializedName("sv")
    val sv: String = "",
    @SerializedName("tr")
    val tr: String = "",
    @SerializedName("zh")
    val zh: String = ""
)

data class TravelGuide(
    @SerializedName("review")
    val review: String = "",
    @SerializedName("thumbnail")
    val thumbnail: String = "",
    @SerializedName("title")
    val title: String = "",
    @SerializedName("url")
    val url: String = ""
)

data class Wikipedia(
    @SerializedName("abstract")
    val `abstract`: String = "",
    @SerializedName("home")
    val home: String = "",
    @SerializedName("image")
    val image: String = "",
    @SerializedName("population")
    val population: Int = 0,
    @SerializedName("thumbnail")
    val thumbnail: String = ""
)

class Lastminutede

data class Destination(
    @SerializedName("time")
    val time: String = ""
)

data class General(
    @SerializedName("countries")
    val countries: String = ""
)

data class Origin(
    @SerializedName("time")
    val time: String = "",
    @SerializedName("zoneInfo")
    val zoneInfo: ZoneInfo = ZoneInfo()
)

data class TimeOffset(
    @SerializedName("offsetMins")
    val offsetMins: Int = 0
)

data class ZoneInfo(
    @SerializedName("description")
    val description: String = "",
    @SerializedName("image")
    val image: String = "",
    @SerializedName("key")
    val key: String = "",
    @SerializedName("lang")
    val lang: String = "",
    @SerializedName("offsetMinutes")
    val offsetMinutes: Int = 0,
    @SerializedName("offsetMinutesFormatted")
    val offsetMinutesFormatted: String = "",
    @SerializedName("page")
    val page: String = "",
    @SerializedName("thumbnail")
    val thumbnail: String = ""
)