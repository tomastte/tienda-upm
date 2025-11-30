# 🏪 Tienda UPM — Práctica POO 2025-2026 (Entrega 2)

## 📘 Descripción

Proyecto desarrollado como **Entrega 2 (E2)** de la asignatura **Programación Orientada a Objetos** (Grado en Ingeniería del Software, ETSISI-UPM) por el **grupo IWSIM22_09**.

En esta versión, se amplía el sistema de la **Entrega 1**, incorporando:

* **Usuarios**: Clientes y Cajeros, con registro, identificación y gestión de alta/baja.
* **Productos avanzados**: Comidas en campus, Reuniones y Productos Personalizables, con reglas de creación y precios específicos.
* **Tickets extendidos**: Asociados a un Cajero y un Cliente, con estados (`VACIO`, `ACTIVO`, `CERRADO`) y operaciones de creación, modificación y cierre.
* **Validaciones temporales y restricciones**:

    * Comidas → mínimo 3 días de antelación.
    * Reuniones → mínimo 12 horas de planificación.
    * Productos personalizables → recargo del 10 % por cada personalización; máximo de textos permitidos.
* Generación de **IDs únicos** para todos los elementos del sistema:

    * Clientes → DNI.
    * Cajeros → `UW` + 7 dígitos aleatorios.
    * Productos → ID numérico o generado automáticamente.
    * Tickets → `YY-MM-dd-HH:mm-#####` y fecha de cierre al imprimir.

---

## 🧱 Entregables
- 🗂️ Código fuente completo en este repositorio.
- 🧾 Ejecutable `.jar` publicado en **Releases**.
- 🧩 Diagrama UML en `/docs`.


---

## 🧩 Arquitectura del proyecto

El sistema sigue el patrón **3 capas** con inyección de dependencias:

| Capa                                                         | Clases principales                                                                    | Descripción                                                                                                   |
| ------------------------------------------------------------ | ------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------- |
| **Modelo (`es.upm.etsisi.poo.app2.data.model`)**             | `Product`, `CustomProduct`, `BasicProduct`, `Ticket`, `Cashier`, `Client`, `Category` | Representan productos, tickets y usuarios, incluyendo reglas de negocio, validaciones y descuentos.           |
| **Repositorio (`es.upm.etsisi.poo.app2.data.repositories`)** | `ProductRepositoryMap`, `CashierRepositoryMap`, `ClientRepositoryMap`                 | Manejo de datos en memoria con IDs únicos y listas de entidades.                                              |
| **Servicios (`es.upm.etsisi.poo.app2.services`)**            | `ProductService`, `CashierService`, `ClientService`                                   | Capa intermedia que valida operaciones y asegura reglas de negocio antes de interactuar con los repositorios. |
| **Vista (`es.upm.etsisi.poo.app2.presentation.view`)**       | `View`                                                                                | Encargada de mostrar información, mensajes de error y listas ordenadas por consola.                           |
| **CLI (`es.upm.etsisi.poo.app2.presentation.cli`)**          | `CommandLineInterface`, `Command`, `ErrorHandler`                                     | Interpretación y ejecución de comandos por consola o desde archivo, con manejo de errores.                    |
| **Aplicación (`es.upm.etsisi.poo.app2`)**                    | `App`, `DependencyInjector`                                                           | Inicializa la aplicación, inyecta dependencias y registra comandos.                                           |

---

## 💻 Comandos implementados

### Clientes / Cajeros

```
client add "<nombre>" <DNI> <email> <cashId>
client remove <DNI>
client list

cash add [<id>] "<nombre>" <email>
cash remove <id>
cash list
cash tickets <id>
```

### Tickets

```
ticket new [<id>] <cashId> <userId>
ticket add <ticketId> <cashId> <prodId> <amount> [--p<texto> --p<texto> ...]
ticket remove <ticketId> <cashId> <prodId>
ticket print <ticketId> <cashId>
ticket list
```

**Notas:**

* `--p<texto>` solo aplica a productos personalizables.
* Reuniones y comidas no se pueden añadir dos veces al mismo ticket.
* Imprimir un ticket **cierra automáticamente** el ticket.
* Solo el cajero que inició el ticket puede modificarlo o imprimirlo.

### Productos

```
prod add [<id>] "<name>" <category> <price> [<maxPers>]
prod update <id> NAME|CATEGORY|PRICE <value>
prod addFood [<id>] "<name>" <price> <expiration: yyyy-MM-dd> <max_people>
prod addMeeting [<id>] "<name>" <price> <expiration: yyyy-MM-dd> <max_people>
prod list
prod remove <id>
```

**Notas:**

* `max_people` para comidas y reuniones → precio calculado por persona.
* Productos personalizables → precio incrementado un 10 % por cada personalización.
* Validaciones temporales: comidas 3 días, reuniones 12 horas antes de la fecha.

### Generales

```
help
echo "<texto>"
exit
```

---

## 📚 Categorías y descuentos

* Categorías: `MERCH`, `STATIONERY`, `CLOTHES`, `BOOK`, `ELECTRONICS`
* Descuentos aplicados si hay ≥2 productos por categoría:

    * MERCH → 0 %
    * STATIONERY → 5 %
    * CLOTHES → 7 %
    * BOOK → 10 %
    * ELECTRONICS → 3 %

---

## ⚙️ Ejecución

1. Asegúrate de tener **Java 22 o superior**.
2. Descarga el `.jar` desde **Releases** o compílalo desde el repositorio.
3. Ejecuta en modo interactivo:

```bash
java -jar tienda-upm-v2.0.0.jar
```

4. Ejecuta con archivo de comandos:

```bash
java -jar tienda-upm-v2.0.0.jar input.txt
```

Se imprimirán los comandos y sus resultados como si fueran interactivos.

---

## 📦 Estructura del repositorio

```
tiendas-upm/
├── src/                          # Código fuente Java
├── docs/                         # Diagrama UML actualizado
├── README.md                     # Este archivo
└── pom.xml / build.gradle        # Configuración de compilación
```

---

## 👥 Autores

| Nombre | Matrícula |
| ------ | --------- |
| Tomás  | bv0374    |
| Marta  | bv0078    |
| Sofía  | bv0143    |
| Alicia | bv0195    |
| Jiling | bv0393    |

---

## 🗓️ Versión

**v2.0.0 — Segunda entrega (E2, 2025-2026)**
Código funcional y ejecutable, validado según el enunciado de la práctica extendida “Tienda UPM”.

---

© 2025 ETSISI-UPM — Proyecto académico de Programación Orientada a Objetos.
