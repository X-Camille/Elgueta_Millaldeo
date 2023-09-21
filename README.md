Integrantes:

Kihara Millaldeo
Camille Elgueta
PASO 2: El error principal del código es que se declara a la matriz registro como de tipo String, cuando debería ser de tipo double, esto último provoca varios errores más tanto en los métodos siguientes, como en la lógica. Otro error que detectamos es en el bucle do - while entre las líneas 20 - 26. La condición del while siempre es verdadera por lo que el bucle es infinito. Los nombres de las variables y los métodos no siguen las buenas prácticas de programación, al no tener nombres descriptivos. En la línea 33 se declara edad como int, pero después se le agrega un valor utilizando nextLine(), el cual devuelve un String