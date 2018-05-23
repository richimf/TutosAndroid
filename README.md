# TutosAndroid
Códigos de varios ejemplos de Android

#Componentes que conforman una App
- Activities
- Fragments
- Broadcast Receivers
- Content Providers
- Services

###Activity
Se puede pensar en una actividad como la "ventana". Este es el componente principal de la interfaz gráfica de una App.

###View
Las vistas (views). Android provee al inicio multiples controles básicos como botones, listas, imagenes, etc.
[View class reference](https://developer.android.com/reference/android/view/View)

### Content Provider

Es un mecanismo para compartir datos entre Apps. Nuestra app podrá acceder a los datos de otra a través de los `content provider` definidos. Este mecanismo es utilizado por muchas de las aplicaciones estandard de un dispositivo Android, como por ejemplo la **lista de contactos**, la aplicación de SMS, o el calendario/agenda. 

Para añadir un content provider a nuestra aplicación tendremos que:

- Crear una nueva clase que extienda a la clase android `ContentProvider`.
- Declarar el nuevo content provider en nuestro fichero `AndroidManifest.xml`


### Broadcast Receiver
Es un componente destinado a **detectar y reaccionar ante determinados mensajes** o eventos globales generados por el sistema ejemplo **Bateria Baja**, Tarjeta SD insetada, o bien, mensajes generados por otras Apps `Intents`, esto no va dirigido a una App concreta, sino a aquellas que escuchan a este evento.


### Intents

Un `Intent` es el elemento básico de comunicacion entre distintos componentes de Android. Se puede entender como los "mensajes" o "peticiones" que son enviados entre los distintos componentes de una App o entre distintas Apps.
Con un Intent puedes:

- Mostrar un nuevo Activity
- Iniciar un `service`
- Enviar un mensaje `broadcast`
- Iniciar otra App
- etc...


###Service
Los servicios son componentes sin interfaz gráfica que **se ejecutan en 2do plano** (operaciones de larga duracion en el background aunque el usuario se cambie de App). Por ejemplo actualizar datos, lanzar notificaciones, **reproducir musica**, abrir archivos, etc...

Un servicio puede ser de dos formas:

- **Iniciado**, un Activity lo inicia con `startService()`, ej. subir un archivo, una vez terminado el upload, el servicio termina.

- **De Enlace**, cuando la App se vincula a este servicio mediante `bindService()`. Un servicio de enlace ofrece una interfaz *cliente-servidor* que permite que los componentes interactúen con el servicio, envíen solicitudes, obtengan resultados e incluso lo hagan en distintos procesos con la comunicación entre procesos IPC. Un servicio de enlace solo funciona si otro componente de app esta enlazado a él. Multiples componentes se pueden enlazar al servicio, pero cuando todos ellos se desenlazan, el servicio muere.


**¿Debes utilizar un servicio o un subproceso?**

Un servicio es simplemente un componente que puede ejecutarse en segundo plano, incluso cuando el usuario no está interactuando con tu aplicación. Por lo tanto, debes crear un servicio solo si eso es lo que necesitas.

Por ejemplo, si deseas reproducir música, pero solo mientras se ejecute tu actividad, puedes crear un subproceso en `onCreate()`, comenzar a ejecutarlo en `onStart()` y luego detenerlo en `onStop()`. También considera la posibilidad de utilizar **AsyncTask o HandlerThread**, en lugar de la clase Thread tradicional. 

Recuerda que si utilizas un *servicio*, este se ejecuta en el subproceso principal de tu aplicación de forma predeterminada, por lo que debes crear un subproceso nuevo dentro del servicio. Para crear un servicio, creamos una subclase de `Service`, y hay que reescribir algunos metodos de callback...

- `onStartCommand()`:  Cuando una Activity solicita iniciar el servicio, llamamos a `startService()`. Una vez que se ejecuta este método, el servicio se inicia y se puede ejecutar en segundo plano de manera indefinida. Si implementas esto, **será tu responsabilidad detener el servicio** cuando tu trabajo esté terminado. Para ello, llama a `stopSelf()` o `stopService()`.
- `onBind()`: El sistema llama a este método cuando otro componente desea enlazarse con el servicio, llamamos a `bindService()`.

[Ver mas sobre servicios](https://developer.android.com/guide/components/services?hl=es)


###Preferencias
Cada preferencia se almacenará en forma de clave-valor, es decir, cada una de ellas estará compuesta por un identificador único. Tenemos `SharedPreferences` y `PreferenceActivity`.

####SharedPreferences (preferencias compartidas)
Además, y a diferencia de SQLite, los datos no se guardan en un fichero binario de base de datos, sino en ficheros **XML** (/data/data/.../shared_prefs/MisPreferencias.xml).

Nos centramos en la clase `SharedPreferences` y obtendremos nuestra data con `getSharedPrefences()`, este método necesita el identificador y el modo de acceso. Este modo de acceso indicará qué aplicaciones tendrán acceso a la colección de preferencias y qué operacioens tendrán permitido realizar sobre ellas.

- ` MODE_PRIVATE`: Solo nuestra app tiene acceso a estas preferencias.
- `MODE_WORLD_READABLE`[deprecated]: Todas las apps pueden leer las pref. pero solo nuestra app puede modificarlas.
- `MODE_WORLD_WRITABLE`[deprecated]: Todas las aplicacioens pueden leer y modificar estas preferencias.

Ej., obtener nuestras preferencias:
```Java
SharedPreferences prefs = getSharedPreferences("MisPreferencias",Context.MODE_PRIVATE);
```

Podemos obtener, insertar o modificar preferencias utilizando los métodos get o put correspondientes al tipo de dato de cada preferencia:
```Java
SharedPreferences prefs =
     getSharedPreferences("MisPreferencias",Context.MODE_PRIVATE);
     
String correo = prefs.getString("email", "por_defecto@email.com");
```
####PreferenceActivity

Un `Preference Activity` es una pantalla donde podremos seleccionar las preferencias de una App. Esta pantalla la definiremos mediante un XML, asi como cualquier layout, aunque este irá en `/res/xml`. El contenedor principal de nuestra pantalla será el elmento `<PreferenceScreen>`. Este elemento representará a la pantalla de opciones en sí, dentro de la cual incluiremos el resto de elementos. Esta pantalla puede tener categorias, estas las especificaremos con `<PreferenceCategory>`, cada categoria tendra todas sus opciones.


### Servicios SOAP

Android NO provee de manera nativa soporte para SOAP, pero hay una libreria que permite acceder a este tipo de servicios Web, hablamos de [ksoap2-android](http://simpligility.github.io/ksoap2-android/index.html).









### Tareas en Segundo Plano, Thread y AsyncTask.

####Thread y AsyncTask

Todos los componentes de una App, Activities, Servicios, Brodcast Receivers se ejecutan en el hilo principal `Main Thread` o `GUI thread`. Aquí es donde se ejecutan todas las operaciones que gestionan la interfaz de usuario de la aplicación. 





















