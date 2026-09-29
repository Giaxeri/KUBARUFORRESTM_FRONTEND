<div align="center">

# Kubaru Forrest M — Frontend

Aplicación web en **Java EE (JSF + PrimeFaces)** para gestionar emisoras de radio en línea y su catálogo de canciones,
con reproductor de audio integrado. Consume una **API REST** mediante peticiones HTTP con JSON.

![Java](https://img.shields.io/badge/Java-ED8B00?style=flat-square&logo=openjdk&logoColor=white)
![JSF](https://img.shields.io/badge/JSF_2.3-007396?style=flat-square)
![PrimeFaces](https://img.shields.io/badge/PrimeFaces_13-1C7ED6?style=flat-square)
![Tomcat](https://img.shields.io/badge/Apache_Tomcat_9-F8DC75?style=flat-square&logo=apachetomcat&logoColor=black)
![REST](https://img.shields.io/badge/API-REST_%2F_JSON-2E7D32?style=flat-square)

</div>

---

## Descripción

Proyecto final desarrollado en equipo (Ingeniería de Sistemas, Universidad El Bosque, 2024-1). Este repositorio
contiene la **capa de presentación** de la plataforma: el usuario registra su emisora, administra las canciones
que emite y las reproduce desde el navegador. Los datos se guardan en un **backend REST** independiente
(servicio en `http://localhost:8088`), con el que el frontend se comunica por HTTP intercambiando JSON.

## Funcionalidades

- **Registro de emisoras**: nombre, tipo de emisora y género musical.
- **Sesión por emisora**: la emisora activa se guarda en la sesión HTTP para que el resto de pantallas trabajen sobre ella.
- **Gestión de canciones (CRUD)**: crear, listar, actualizar y eliminar canciones con nombre, artista, género y archivo de audio.
- **Reproductor de audio** en el navegador (HTML5 `<audio>`) para escuchar las canciones de la emisora.
- **Interfaz con PrimeFaces**: tablas de datos, paneles, menús de selección y formularios validados.

## Arquitectura

```
┌─────────────────────────────┐        HTTP + JSON         ┌─────────────────────────┐
│  Frontend (este repositorio)│  GET / POST / PUT / DELETE │  Backend REST           │
│                             │ ─────────────────────────► │  localhost:8088         │
│  Vistas .xhtml (PrimeFaces) │                            │  /Emisora/*             │
│        ▲         │          │ ◄───────────────────────── │  /Canciones/*           │
│        │         ▼          │                            └────────────┬────────────┘
│  Managed Beans (JSF)        │                                         │
│        │                    │                                         ▼
│  DAO (HttpURLConnection) ───┼──►                                 Base de datos!
│  DTO                        │
└─────────────────────────────┘
```

| Capa | Paquete / carpeta | Responsabilidad |
|---|---|---|
| Vista | `src/main/webapp/*.xhtml` | Pantallas JSF con componentes PrimeFaces |
| Controlador | `co.edu.unbosque.model` | `EmisoraBean`, `CancionBean` y `CookiesBean` (sesión) |
| Acceso a datos | `co.edu.unbosque.dao` | Clientes HTTP de la API REST y conversión JSON ↔ objetos (json-simple) |
| Transferencia | `co.edu.unbosque.dto` | `EmisoraDTO` y `CancionDTO` |

### Endpoints consumidos

| Recurso | Método | Ruta |
|---|---|---|
| Emisoras | `GET` · `POST` · `PUT` · `DELETE` | `/Emisora/listar` · `/Emisora/guardar` · `/Emisora/actualizar` · `/Emisora/eliminar` |
| Canciones | `GET` · `POST` · `PUT` · `DELETE` | `/Canciones/listar` · `/Canciones/guardar` · `/Canciones/actualizar/{nombre}` · `/Canciones/eliminar/{nombre}` |

## Ejecución

**Requisitos**

- JDK 8 o superior
- Apache Tomcat 9
- Eclipse IDE for Enterprise Java (o IntelliJ IDEA Ultimate)
- El backend REST ejecutándose en `http://localhost:8088`

**Pasos**

1. Clona el repositorio e impórtalo en Eclipse como **Existing Project** (es un *Dynamic Web Project*).
2. Configura Apache Tomcat 9 como servidor de ejecución.
3. Verifica que las librerías de `src/main/webapp/WEB-INF/lib` estén en el *build path*
   (PrimeFaces 13, json-simple y MySQL Connector/J) junto con una implementación de JSF 2.3 (por ejemplo, Mojarra).
4. Ejecuta el proyecto con **Run As → Run on Server** y abre:
   ```
   http://localhost:8080/KUBARUFORRESTM_FRONTEND/faces/index.xhtml
   ```

## Estructura

```
src/main/
├── java/co/edu/unbosque/
│   ├── dao/            # CancionDAO, EmisoraDAO — clientes de la API REST
│   ├── dto/            # CancionDTO, EmisoraDTO
│   └── model/          # Managed beans: EmisoraBean, CancionBean, CookiesBean
└── webapp/
    ├── index.xhtml             # Registro / selección de emisora
    ├── gestioncanciones.xhtml  # Gestión de canciones
    ├── reproductor.xhtml       # Reproductor de audio
    └── WEB-INF/                # web.xml, faces-config.xml y librerías
```

## Equipo

Proyecto desarrollado en equipo. Aporte de **Gianfranco Peniche Uribe** ([@Giaxeri](https://github.com/Giaxeri)):
configuración inicial del repositorio, interfaz de la página principal y de gestión de canciones, y el reproductor de audio.
