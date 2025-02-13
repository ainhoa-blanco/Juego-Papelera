## Juego de la papelera

Este es un juego desarrollado en Java, en el cual el jugador debe atrapar objetos reciclables en un contenedor de basura en movimiento. El objetivo es conseguir la mayor cantidad de puntos antes de quedarse sin vidas.

## Descripción
El juego presenta una interfaz gráfica donde:
- El jugador mueve una papelera con las flechas del teclado.
- Los papeles caen desde la parte superior de la pantalla de manera aleatoria.
- Si la papelera atrapa un papel, el jugador gana 10 puntos.
- Si un papel cae fuera de la papelera, el jugador pierde una vida.
- Se muestra un temporizador y una opción de pausa/reanudar la partida.

## Características
- **Interfaz gráfica**.
- **Eventos de teclado y ratón** para controlar el juego.
- **Sistema de puntuación y vidas**.
- **Temporizador en tiempo real**.
- **Uso de imágenes para representar objetos**.

## Visuals
El juego utiliza imágenes almacenadas en `src/images/` para representar los objetos. Algunas de ellas incluyen:
- `trash.png`: Imagen del contenedor de basura.
- `paper.png`: Imagen de los objetos reciclables.
- `politecnics.png`: Icono de la ventana del juego.

## Instalación
1. Clonar el repositorio:
   ```bash
   git clone https://github.com/ainhoa-blanco/Juego-Papelera.git
   ```
2. Asegúrate de tener **Java 8 o superior** instalado.
3. Compilar el código:
   ```bash
   javac Main.java
   ```
4. Ejecutar el juego:
   ```bash
   java Main
   ```

## Uso
1. Mueve el contenedor con las **flechas izquierda y derecha**.
2. Captura los papeles antes de que caigan al suelo.
3. Si fallas, perderás una vida.
4. Pulsa **Pausa/Reanudar** para detener o continuar el juego.
5. Gana la mayor cantidad de puntos posible.

## Autor
Desarrollado por Ainhoa Blanco.

## Licencia
Este proyecto está bajo la **licencia MIT**, por lo que puedes usarlo, modificarlo y distribuirlo libremente.
