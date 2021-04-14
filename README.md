# Like Animation
## _Android property animation, Instagram Like animation example_

El repositorio tiene como objetivo ayudar a quien lo necesite, a dar los primeros pasos en animaciones Android.

La documentación oficial para realizar animaciones con Property Animations está en [Property Animations](https://developer.android.com/guide/topics/graphics/prop-animation)

## Características

- Animación de like (Cambio de tamaño) al cliquear en el post
- Opción en código Kotlin
- Opción en recurso animator

Cómo cambiar entre opción kotlin y recurso?
-------------------
En en la clase MainActivity.kt comentar/descomentar el método que se quiera probar

```kotlin
private fun setupUI() {
        loadImages()
        binding.imgPost.setOnClickListener {
            //Usar uno de los 2 métodos!
            //animateRes()
            //animateCode()
        }
    }
```


## License

MIT
