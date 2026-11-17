-- Ejecutar UNA vez, despues de arrancar la app por primera vez
-- (ddl-auto=update ya creo las tablas roles, usuarios, ejercicios, evaluaciones)

insert into roles (nombre_rol, descripcion_rol, fecha_creacion_rol) values
  ('ADMIN',      'Administrador del sistema', now()),
  ('DOCENTE',    'Docente',                   now()),
  ('ESTUDIANTE', 'Estudiante',                now()),
  ('APODERADO',  'Apoderado',                 now());

-- Usuario: admin@colegio.com   Contrasena: admin123
insert into usuarios (nombre, apellido, email, password_hash, rol_id, estado, fecha_registro)
values ('Admin', 'Sistema', 'admin@colegio.com',
        '$2b$12$VV91/prDyUwOk3TLSMAQuu4plyrKaKqTzu1MD8s8RVHIFhDhKmggy',
        (select id from roles where nombre_rol = 'ADMIN'), 'ACTIVO', now());
