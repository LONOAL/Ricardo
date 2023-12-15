package com.dam.riotapitest


import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import services.ClientApi
import services.ParamProperties
import services.PlatformRoutes
import services.endpoints.ChampionInfo
import services.endpoints.ChampionMasteryDTO
import services.endpoints.SummonerDTO
import services.interceptors.TokenProvider
import java.io.File
import java.util.concurrent.Executors

class Api {


    fun main() {



        ClientApi.championV3(PlatformRoutes.EUW1).getChampionRotations().enqueue(object : Callback<ChampionInfo> {
            override fun onFailure(call: Call<ChampionInfo>, t: Throwable) {
                println(t.message)
            }

            override fun onResponse(call: Call<ChampionInfo>, response: Response<ChampionInfo>) {
                response.body()?.let { championInfo ->
                    // Realiza operaciones en championInfo
                    println("Champion Info: ${championInfo.freeChampionIds}")
                    // Otras operaciones...
                }

            }
        })


        ClientApi.summonerV4(PlatformRoutes.EUW1).getSummonerByName("LONOAL02").enqueue(object : Callback<SummonerDTO>{
            override fun onResponse(call: Call<SummonerDTO>, response: Response<SummonerDTO>) {
                response.body()?.let { summonerDTO ->
                    println("Summoner Name: ${summonerDTO.name}")
                    println("Summoner Level: ${summonerDTO.summonerLevel}")
                    println("Summoner Id: ${summonerDTO.id}")


                }
            }

            override fun onFailure(call: Call<SummonerDTO>, t: Throwable) {
                t.printStackTrace()
                println(t.message)
            }
        }
        )



        ClientApi.championMasteryV4(PlatformRoutes.EUW1).getChampionMasteriesBySummonerAndChampion(getEncryptedSummonerIdByName("LONOAL02"),33).enqueue(object : Callback<ChampionMasteryDTO>{
            override fun onResponse(call: Call<ChampionMasteryDTO>, response: Response<ChampionMasteryDTO>
            ) {
                response.body()?.let { championMasteryDTO ->
                    println("Summoner Champion Mastery: ${championMasteryDTO.championPoints}")
                }
            }

            override fun onFailure(call: Call<ChampionMasteryDTO>, t: Throwable) {
                t.printStackTrace()
                println(t.message)
            }


        })



    }
    fun getSummonerIdByName(name: String): String {
        var sumId : String = ""
        ClientApi.summonerV4(PlatformRoutes.EUW1).getSummonerByName(name).enqueue(object : Callback<SummonerDTO>{
            override fun onResponse(call: Call<SummonerDTO>, response: Response<SummonerDTO>) {
                response.body()?.let { summonerDTO ->
                    sumId= summonerDTO.id
                }
            }

            override fun onFailure(call: Call<SummonerDTO>, t: Throwable) {
                t.printStackTrace()
            }
        }
        )
        return sumId
    }

    fun initApi(){
        // OR simply put
        ClientApi.apply {
            tokenProvider = object : TokenProvider {
                override fun getToken(): String {
                    return "RGAPI-53007f22-8474-4b17-ae62-9672c21927fb"
                }
            }
        }
    }
    // Utilizamos un Executor para realizar la llamada a la API en un hilo separado
    private val executor = Executors.newSingleThreadExecutor()

    fun getEncryptedSummonerIdByName(name: String): String {
        var sumId: String? = null

        executor.execute {
            try {
                val response = ClientApi.summonerV4(PlatformRoutes.EUW1).getSummonerByName(name).execute()
                if (response.isSuccessful) {
                    val summonerDTO = response.body()
                    sumId = summonerDTO?.id
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        // Esperamos un tiempo razonable para que la llamada de red se complete
        Thread.sleep(2000) // Ajusta el tiempo de espera según tus necesidades

        // Verificamos si sumId es null (si hubo un error) o si tiene un valor
        return sumId ?: "Error obteniendo el Summoner ID"
    }


    fun getChampionIdByName(nombreCampeon: String): Long {
        var archivo = File("C:\\Users\\PC-LORENZO\\Documents\\Teleco\\Datos\\equide\\src\\main\\kotlin\\ChampionId.txt")
        val lineas = archivo.readLines()
        for (linea in lineas) {
            val partes = linea.trim().split(":")
            if (partes.size == 2) {
                val id = partes[0].trim()
                var nombre = partes[1].trim().removeSurrounding("\"")
                if (nombre.equals(nombreCampeon)) {
                    return id.toLong()
                }
            }
        }
        return 33
    }


    fun getMasteryPoints(sumName: String, champName: String): Int {
        var masPoints : Int? = null

                ClientApi.championMasteryV4(PlatformRoutes.EUW1).getChampionMasteriesBySummonerAndChampion(getEncryptedSummonerIdByName(sumName),getChampionIdByName(champName)).enqueue(object : Callback<ChampionMasteryDTO>{
                    override fun onResponse(call: Call<ChampionMasteryDTO>, response: Response<ChampionMasteryDTO>
                    ) {
                        response.body()?.let { championMasteryDTO ->
                            masPoints = championMasteryDTO.championPoints
                        }
                    }

                    override fun onFailure(call: Call<ChampionMasteryDTO>, t: Throwable) {
                        t.printStackTrace()
                        println(t.message)
                    }
                }
                )


        Thread.sleep(2000)

        return masPoints ?: 0
    }
}