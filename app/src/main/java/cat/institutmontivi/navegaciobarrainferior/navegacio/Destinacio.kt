package cat.institutmontivi.navegaciobarrainferior.navegacio

import android.media.Image
import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import cat.institutmontivi.navegaciobarrainferior.R

enum class CategoriaDeNavegacio(
    val rutaPrevia: String,
    val icona: ImageVector,
    @StringRes val titol: Int
){
    CategoriaA("CategoriaA", Icons.Default.Home, R.string.cat_a),
    CategoriaB("CategoriaB", Icons.Default.Call, R.string.cat_b),
    CategoriaC("CategoriaC", Icons.Default.Lock, R.string.cat_c),
}

sealed class Destinacio(
    val rutaBase: String,
    val argumentsDeNavegacio: List <ArgumentDeNavegacio> = emptyList()
)
{

    val navArgs = argumentsDeNavegacio.map { it.toNavArgument()}
    /**
     * Propietat que crea l'string amb la ruta base i tots els arguments separats per barres
     */
    val rutaGenerica = run {
        //cal construir un string tipus: rutabase/{arg1}/{arg2} ...
        val clausArguments = argumentsDeNavegacio.map{"{${it.clau}}"}
        listOf(rutaBase)
            .plus(clausArguments)
            .joinToString("/")
    }

    object LlistaA: Destinacio(CategoriaDeNavegacio.CategoriaA.rutaPrevia + "/LlistaA")
    object LlistaB: Destinacio(CategoriaDeNavegacio.CategoriaB.rutaPrevia + "/LlistaB")
    object LlistaC: Destinacio(CategoriaDeNavegacio.CategoriaC.rutaPrevia + "/LlistaC")
    object DetallA:Destinacio(CategoriaDeNavegacio.CategoriaA.rutaPrevia + "/DetallA", listOf(ArgumentDeNavegacio.IdA)){
        fun CreaRutaAmbArguments (idA:Int) = "$rutaBase/$idA"
    }
    object DetallB:Destinacio(CategoriaDeNavegacio.CategoriaB.rutaPrevia + "/DetallB", listOf(ArgumentDeNavegacio.IdB)){
        fun CreaRutaAmbArguments (idB:Int) = "$rutaBase/$idB"
    }
    object DetallC:Destinacio(CategoriaDeNavegacio.CategoriaC.rutaPrevia + "/DetallC", listOf(ArgumentDeNavegacio.IdC)){
        fun CreaRutaAmbArguments (idC:Int) = "$rutaBase/$idC"
    }
}

//NavType<*> ens permet identificar qualsevol tipus de NavType
enum class ArgumentDeNavegacio (val clau: String, val tipus: NavType<*>){
    IdA("IdA", NavType.IntType),
    IdB("IdB", NavType.IntType),
    IdC("IdC", NavType.IntType);

    fun toNavArgument (): NamedNavArgument {
        return navArgument(clau) {type = tipus}
    }
}
