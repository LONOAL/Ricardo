import com.dam.riotapitest.Api

fun main(args: Array<String>) {
    Api().initApi()
    Api().main()
    println(Api().getMasteryPoints("LONOAL02","Swain"))
}