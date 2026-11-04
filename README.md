# Plantilla · Fragmentos + Navigation + Safe Args

Proyecto de Android Studio listo para empezar una app con **fragmentos**, el
**componente Navigation** (`NavHostFragment` + grafo) y **Safe Args**. Trae todo
Gradle configurado y un ejemplo mínimo que funciona: dos fragmentos, una acción
con animación y un argumento de texto.

![Flujo de navegación de la plantilla](docs/flujo-de-navegacion.svg)

## Qué trae

```
PlantillaNavegacion/
├── settings.gradle.kts         ← nombre del proyecto
├── build.gradle.kts            ← raíz: plugins DECLARADOS (apply false)
├── gradle/libs.versions.toml   ← TODAS las versiones
└── app/
    ├── build.gradle.kts        ← plugins APLICADOS, viewBinding, dependencias
    └── src/main/
        ├── java/es/sanclemente/plantilla/
        │   ├── MainActivity.kt      ← barra + NavHostFragment
        │   ├── InicioFragment.kt    ← navega con Directions
        │   └── DetalleFragment.kt   ← recibe con navArgs()
        └── res/
            ├── navigation/nav_graph.xml
            ├── layout/  activity_main, fragment_inicio, fragment_detalle
            ├── anim/    entrar/sair dereita/esquerda
            └── values/, values-es/, values-night/
```

## Qué zip usar según tu Android Studio

Cada versión de Android Studio admite el Android Gradle Plugin (AGP) solo hasta
cierto número. Si el proyecto pide uno más nuevo, el *Sync* falla. Por eso hay
dos variantes de la plantilla, con el mismo código y distintas versiones:

| | **Rabbit** (2026.2) | **Panda** (2025.3) |
|---|---|---|
| Zip | `PMDM-plantilla-navegacion.zip` | `PMDM-plantilla-navegacion-panda.zip` |
| AGP admitido como máximo | 9.4 (Rabbit 1) · 9.5 (Rabbit 2) | 9.0 (Panda 1) · 9.1 (Panda 2 y 3) · 9.2 (Panda 4) |
| AGP de la plantilla (`agp`) | 9.4.1 | 9.0.1 (vale para todas las Panda) |
| Gradle (wrapper) | 9.6.0 | 9.1.0 |
| Navigation **y** Safe Args (`navigation`) | 2.10.2 | 2.9.6 |
| compileSdk / targetSdk / minSdk | 37 / 36 / 24 | 36 / 36 / 24 |
| core-ktx · appcompat · activity | 1.19.1 · 1.8.0 · 1.13.0 | 1.17.0 · 1.7.1 · 1.11.0 |
| material · constraintlayout | 1.14.0 · 2.2.2 | 1.13.0 · 2.2.1 |

**Cómo saber cuál tienes:** **Help → About** (en macOS, **Android Studio → About
Android Studio**). La primera línea dice, por ejemplo, *Android Studio Panda 3 | 2025.3.3*.

**Por qué en Panda cambian también las bibliotecas:** androidx.core 1.18 y
posteriores exigen compilar contra la API 36.1, que AGP 9.0 no admite. Las
versiones de la columna de Panda son de la misma época que ese AGP.

Compatibilidad AGP → Navigation/Safe Args: **8.x → 2.8.x · 9.0 → 2.9.6 o posterior · 9.4 → 2.10.2**.
Con AGP 9.0, Safe Args 2.9.5 **no** sirve: todavía busca la API antigua del plugin de
Android, que AGP 9 ya no expone, y falla con *safeargs plugin must be used with android
plugin*. Desde 2.9.6 usa la API nueva.
Navigation y Safe Args comparten la línea `navigation` del catálogo, así que
nunca quedan con versiones distintas.

### Pasar a mano de una variante a otra

Si abriste en Panda el zip de Rabbit (o al revés), basta con cambiar tres archivos
y volver a hacer *Sync*:

1. `gradle/libs.versions.toml`: las siete líneas de `[versions]`, con los valores de la columna que toque.
2. `app/build.gradle.kts`: `compileSdk` (37 en Rabbit, 36 en Panda).
3. `gradle/wrapper/gradle-wrapper.properties`: en `distributionUrl`, `gradle-9.6.0-bin.zip` (Rabbit) o `gradle-9.1.0-bin.zip` (Panda).

No hace falta tocar nada más: el código Kotlin y los XML son idénticos.

## Abrirla

1. Descomprime el zip en una carpeta tuya.
2. **File → Open** → la carpeta `PlantillaNavegacion` (la que contiene `settings.gradle.kts`).
3. Deja que haga el *Sync*. La primera vez descarga Gradle y las dependencias.
4. Si pide instalar la plataforma Android (**API 37** en Rabbit, **API 36** en Panda), acepta.
   Si te ofrece actualizar el AGP con el *AGP Upgrade Assistant*, **no lo hagas**:
   cambiaría `agp` sin tocar `navigation` y Safe Args dejaría de funcionar.
5. **Build → Assemble Project**. Con eso se generan las clases de Safe Args.

## Empezar un proyecto nuevo a partir de ella

**Lo mínimo (sin riesgo):** cambia estas tres cosas y ya tienes una app distinta
que se instala al lado de las demás.

| Qué | Dónde |
|---|---|
| Nombre del proyecto | `settings.gradle.kts` → `rootProject.name` |
| Identificador de la app | `app/build.gradle.kts` → `applicationId` |
| Nombre en el móvil | `res/values/strings.xml` y `values-es/strings.xml` → `app_name` |

**Cambiar también el paquete** (`es.sanclemente.plantilla`) es opcional. Si lo
haces, tienen que coincidir **cuatro sitios**:

1. `namespace` en `app/build.gradle.kts`.
2. Las carpetas `java/es/sanclemente/plantilla/`.
3. La línea `package` de cada `.kt` y los `import ….databinding.…`.
4. Los `android:name` de `nav_graph.xml`.

Android Studio lo hace casi todo con **clic derecho sobre el paquete → Refactor → Rename**;
después revisa el punto 4 a mano y haz **Build → Clean Project** + **Build → Assemble Project**.

## Añadir una pantalla nueva

1. Crea el layout `fragment_loquesea.xml` y la clase `LoqueseaFragment.kt`
   (copia `DetalleFragment.kt` y cambia el nombre del binding).
2. En `nav_graph.xml`: añade el `<fragment>` con su `android:id`, `android:name` y `android:label`.
3. Añade la `<action>` **dentro** del fragmento de origen, con `app:destination`.
4. Si recibe datos, añade los `<argument>` **dentro** del fragmento de destino.
5. **Build → Assemble Project**. Ahora existen `…Directions.accion…()` y `…Args`.
6. Navega con `findNavController().navigate(…Directions.accion…(datos))`
   y recibe con `private val args: …Args by navArgs()`.

Tipos de argumento más usados: `string`, `integer`, `boolean`, `float`, `long`.
Para dar un valor por omisión: `android:defaultValue="0"`.

## Las reglas de Gradle que no se tocan

- **Raíz**: los plugins con `apply false`. Safe Args toma la versión del catálogo.
- **Módulo**: Safe Args **sin versión**, **sin** `apply false` y **el último** del bloque `plugins`.
- Siempre `androidx.navigation.safeargs.kotlin`, **nunca** `safeargs` a secas (genera Java).
- **No** añadas `org.jetbrains.kotlin.android`: desde AGP 9.0 Kotlin va integrado y aplicarlo da error.

## Si algo falla

| Síntoma | Causa y solución |
|---|---|
| `safeargs plugin must be used with android plugin` | Safe Args demasiado antiguo para ese AGP. Sube `navigation` según la tabla de compatibilidad (con AGP 9.0, como mínimo 2.9.6) y vuelve a hacer *Sync*. |
| `Unresolved reference: InicioFragmentDirections` (o `…Args`) | Las clases aún no se han generado. **Build → Assemble Project**. Si sigue: **Clean Project** + **Assemble Project**. |
| *The project is using an incompatible version (AGP 9.4.1) of the Android Gradle plugin* | Abriste en Panda la variante de Rabbit. Usa el zip de Panda o haz los tres cambios de «Pasar a mano de una variante a otra». |
| El *Sync* falla nada más abrir por otra causa | Versiones del catálogo que tu Android Studio no conoce. Ajusta `agp` y `navigation` según la tabla de variantes. |
| `Dependency … requires libraries and applications that depend on it to compile against version 36.1 (or 37) of the Android APIs` | Una biblioteca más nueva que tu `compileSdk`. En Panda, usa las versiones de su columna; no subas `compileSdk` por encima de 36. |
| La app se cierra al llegar a un destino | Falta un argumento obligatorio: la acción no lo pasa o el `<argument>` está en otro fragmento. |
| Cambié el grafo y no veo el cambio | **Assemble Project** otra vez. |

En el menú **Build**: *Make* equivale a **Assemble Project**; *Rebuild* equivale
a **Clean Project** + **Assemble Project**. No uses **Compile All Sources**:
se salta el procesamiento de recursos y no genera Safe Args ni ViewBinding.

## Lista de prueba

- [ ] Arranca en «Inicio», con título en la barra y **sin** flecha.
- [ ] Escribir un texto y pulsar «Seguinte» desliza a «Detalle» y muestra el texto.
- [ ] Sin escribir nada, «Detalle» muestra «(sen mensaxe)».
- [ ] La flecha de la barra, el botón «Volver» y el «atrás» del sistema hacen lo mismo.
- [ ] Girar en «Detalle» no pierde el texto.
- [ ] Con el emulador en castellano, los textos salen en castellano.
- [ ] Modo oscuro: la app sigue siendo legible.


