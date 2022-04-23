package com.johnowl.hackathon

import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVPrinter
import java.io.FileWriter
import java.math.BigDecimal
import java.time.LocalDate

// beach, historical, night_life, country_side, wine

data class User(val preferences: List<String>, val location: String, val balance: BigDecimal)
data class City(val name: String, val location: String, val characteristics: List<String>, val nearestAirport: String)
data class Flight(val number: String, val from: String, val to: String, val price: BigDecimal)

const val BEACH = "beach"
const val HISTORICAL = "historical"
const val NIGHT_LIFE = "night_life"
const val COUNTRY_SIDE = "country_side"
const val WINE = "wine"

fun main() {

    val users = setOf(
        User(listOf(BEACH), "NETHERLANDS", BigDecimal(100)),
        User(listOf(HISTORICAL), "NETHERLANDS", BigDecimal(300)),
        User(listOf(WINE, HISTORICAL), "NETHERLANDS", BigDecimal(500)),
        User(listOf(BEACH, NIGHT_LIFE), "NETHERLANDS", BigDecimal(1500)),
        User(listOf(WINE, COUNTRY_SIDE), "NETHERLANDS", BigDecimal(300)),
    )

    val cities = setOf(
        City("Paris", "FRANCE", listOf(HISTORICAL, NIGHT_LIFE), "CDG"),
        City("London", "ENGLAND", listOf(HISTORICAL, NIGHT_LIFE), "LHR"),
        City("Ibiza", "SPAIN", listOf(BEACH, NIGHT_LIFE), "IBZ"),
        City("Mallorca", "SPAIN", listOf(BEACH, NIGHT_LIFE), "PMI"),
    )

    val flights = setOf(
        Flight("1234", "AMS", "CDG", BigDecimal(80)),
        Flight("1235", "AMS", "LHR", BigDecimal(120)),
        Flight("1236", "AMS", "IBZ", BigDecimal(170)),
        Flight("1237", "AMS", "PMI", BigDecimal(300)),
    )

    val preference = BEACH
    val balance = BigDecimal(200)

    val recommendedCities = cities.filter { it.characteristics.contains(preference) }
    val recommendedFlights = flights.filter { flight ->
        recommendedCities.any { city -> city.nearestAirport == flight.to && flight.price <= balance }
    }

    println(recommendedFlights)

//    val writer = FileWriter("flights.csv")
//    val csvWriter = CSVPrinter(writer, CSVFormat.DEFAULT)
//
//    for (days in 1L..90) {
//        val date = LocalDate.now().plusDays(days)
//
//        listOf("LHR", "CDG").forEach { destination ->
//            val price = generateRandomBigDecimalFromRange(BigDecimal.valueOf(5  0), BigDecimal.valueOf(69))
//            csvWriter.printRecord("AMS", destination, date, price)
//        }
//    }
//
//    csvWriter.flush()
//    csvWriter.close()
}

fun generateRandomBigDecimalFromRange(min: BigDecimal, max: BigDecimal): BigDecimal? {
    val randomBigDecimal = min.add(BigDecimal(Math.random()).multiply(max.subtract(min)))
    return randomBigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP)
}
