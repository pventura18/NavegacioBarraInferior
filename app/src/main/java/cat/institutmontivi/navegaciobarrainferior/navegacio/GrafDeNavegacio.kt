package cat.institutmontivi.navegaciobarrainferior.navegacio

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import cat.institutmontivi.navegaciobarrainferior.ui.pantalles.DetallA
import cat.institutmontivi.navegaciobarrainferior.ui.pantalles.DetallB
import cat.institutmontivi.navegaciobarrainferior.ui.pantalles.DetallC
import cat.institutmontivi.navegaciobarrainferior.ui.pantalles.LlistaA
import cat.institutmontivi.navegaciobarrainferior.ui.pantalles.LlistaB
import cat.institutmontivi.navegaciobarrainferior.ui.pantalles.LlistaC

@Composable
fun GrafDeNavegacio (controladorDeNavegacio: NavHostController = rememberNavController())
{
    NavHost(navController = controladorDeNavegacio, startDestination = CategoriaDeNavegacio.CategoriaA.rutaPrevia)
    {
        navigation(startDestination = Destinacio.LlistaA.rutaBase, route = CategoriaDeNavegacio.CategoriaA.rutaPrevia){
            composable(route = Destinacio.LlistaA.rutaGenerica){
                LlistaA(
                    llista= (1..100).toList(),
                    onClickElement =  {numero:Int ->controladorDeNavegacio.navigate(Destinacio.DetallA.CreaRutaAmbArguments(numero)) })
            }
            composable (route = Destinacio.DetallA.rutaGenerica, arguments = Destinacio.DetallA.navArgs){
                val numero = it.arguments?.getInt(ArgumentDeNavegacio.IdA.clau)
                requireNotNull(numero)
                DetallA(numero = numero)
            }


        }

        navigation(startDestination = Destinacio.LlistaB.rutaBase, route = CategoriaDeNavegacio.CategoriaB.rutaPrevia){
            composable(route = Destinacio.LlistaB.rutaGenerica){
                LlistaB(
                    llista= ('A'..'Z').toList(),
                    onClickElement =  {caracter:Char ->controladorDeNavegacio.navigate(Destinacio.DetallB.CreaRutaAmbArguments(caracter.code)) })
            }

            composable (route = Destinacio.DetallB.rutaGenerica, arguments = Destinacio.DetallB.navArgs){
                val numero = it.arguments?.getInt(ArgumentDeNavegacio.IdB.clau)
                requireNotNull(numero)
                val caracter = numero.toChar()
                DetallB(lletra = caracter)
            }
        }

        navigation(startDestination = Destinacio.LlistaC.rutaBase, route = CategoriaDeNavegacio.CategoriaC.rutaPrevia){
            composable(route = Destinacio.LlistaC.rutaGenerica){
                LlistaC(
                    llista= ('⌂'..'■').toList(),
                    onClickElement =  {caracter:Char ->controladorDeNavegacio.navigate(Destinacio.DetallC.CreaRutaAmbArguments(caracter.code)) })
            }
            composable (route = Destinacio.DetallC.rutaGenerica, arguments = Destinacio.DetallC.navArgs){
                val numero = it.arguments?.getInt(ArgumentDeNavegacio.IdC.clau)
                requireNotNull(numero)
                val caracter = numero.toChar()
                DetallC(caracter = caracter)
            }
        }
    }
}