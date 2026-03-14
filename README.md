# 🏨 Hotel Reservation System

Sistema backend para la gestión de **reservas hoteleras HospeDATE**, desarrollado con **Java y Spring Boot**.  
Permite administrar usuarios, habitaciones, reservas, servicios adicionales y pagos asociados a cada reserva.

El sistema está diseñado siguiendo una estructura relacional que facilita el control de disponibilidad de habitaciones, registro de clientes y seguimiento de pagos.

---

# 🚀 Tecnologías utilizadas

- **Java 17+**
- **Spring Boot**
- **Spring Data JPA**
- **Hibernate**
- **MySQL**
- **Maven**
- **IntelliJ IDEA**

---

# ✨ Funcionalidades principales

- 👤 Gestión de usuarios (clientes y administradores)
- 🏨 Gestión de habitaciones
- 📅 Creación y administración de reservas
- 🗓 Registro de fechas de check-in y check-out
- 🛎 Gestión de servicios adicionales
- 💳 Registro de pagos
- 📊 Control de estado de reservas

---

# 🗄️ Modelo de Base de Datos

El sistema está compuesto por las siguientes entidades principales:

### Usuario
Almacena la información de los usuarios del sistema.

Campos principales:

- `id_usuario`
- `nombre`
- `apellido`
- `correo`
- `telefono`
- `numero_doc`
- `tipo_doc`
- `rol (ADMIN | CLIENTE)`
- `contrasena`

---

### Habitación

Contiene la información de las habitaciones disponibles en el hotel.

Campos:

- `id_habitacion`
- `numero`
- `capacidad`
- `tipo`
- `precio_por_noche`
- `url_foto`

---

### Reserva

Representa la reserva realizada por un usuario.

Campos:

- `id_reserva`
- `creada_en`
- `estado (ACTIVA | CANCELADA | CONFIRMADA | FINALIZADA | PENDIENTE)`
- `notas`
- `id_habitacion`
- `id_usuario`

---

### Fecha Reserva

Define el período de la reserva.

Campos:

- `id_fechas`
- `check_in`
- `check_out`
- `noches`
- `id_reserva`

---

### Servicios Adicionales

Servicios extras que pueden agregarse a una reserva.

Campos:

- `id_servicio`
- `nombre`
- `precio`

---

### Reserva Servicios

Tabla intermedia que relaciona las reservas con los servicios adicionales.

Campos:

- `id_reserva`
- `id_servicio`

---

### Pago

Registra los pagos asociados a una reserva.

Campos:

- `id_pago`
- `metodo (EFECTIVO | TARJETA | TRANSFERENCIA)`
- `moneda`
- `monto_pagado`
- `monto_total`
- `saldo_pendiente`
- `id_reserva`

---

# 🔗 Relaciones principales

- Un **usuario** puede tener múltiples **reservas**
- Una **habitación** puede tener múltiples **reservas**
- Una **reserva** tiene una **fecha de reserva**
- Una **reserva** puede tener múltiples **servicios adicionales**
- Una **reserva** puede tener uno o varios **pagos**

