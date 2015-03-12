# Android Clean Architecture and MVP

This sample project shows how to implement [Clean Architecture](http://blog.8thlight.com/uncle-bob/2012/08/13/the-clean-architecture.html) (Or any other similar architecture) to an Android project. In short with a clean architecture your code is splitted across various layers, with the objective to separate the business logic code from the code of the UI, database, etc.

The core of the app (Entities and use cases layers of clean architecture) are implemented with a pure java project. This is good because it forces your core code to not have any dependency with Android system. And if your core code is not dependant from Android SDK, it will be following the dependency rule. In addition that will be easier to unit test your code since to run the test will not require any Android instrumentation framework.

The UI is implemented using [MVP](http://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93presenter) (Model View Presenter). The *Presenter* is implemented using a normal Java class that doesn’t extend any Android framework classe (*Activity*, *Fragment*, *View*, etc). The only special thing that have these *Presenter* classes, are that they implement an interface called [Presenter](app/src/main/java/cat/ppicas/cleanarch/ui/presenter/Presenter.java). The *View* are simply a typical Android UI class (*Activity*, *Fragment* or *View*) that implements an interface called [Vista](app/src/main/java/cat/ppicas/cleanarch/ui/vista/Vista.java). Here we chose *Vista* instead of *View* to avoid name clashing with Android view classes (Thanks @gergokajtar for the tip! ;).

The best part comes on how this implementation supports configuration changes. This project introduces the [PresenterHolder](app/src/main/java/cat/ppicas/cleanarch/ui/presenter/PresenterHolder.java), that it will be in charge of manage all the *Presenter* instances used inside an Activity context. The trick is to implement this *PresenterHolder* with a [Fragment](app/src/main/java/cat/ppicas/cleanarch/ui/fragment/PresenterHolderFragment.java) that have the retain instance flag set to true ([setRetainInstance](http://developer.android.com/reference/android/app/Fragment.html#setRetainInstance%28boolean%29)). With this flag set to *true*, the *PresenterHolder Fragment* will survive to any configuration change, and by extension all the presenters that are managed by this holder.

## Features of this sample

* Allows the user to search for the current weather of any city of the world.
* Shows the weather forecast of a city for the next 3 days.
* Uses [OpenWeatherMap](http://openweathermap.org/) APIs to query the data.
* Implements [Clean Architecture](http://blog.8thlight.com/uncle-bob/2012/08/13/the-clean-architecture.html).
* Separated Java only project for business logic code.
* [MVP](http://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93presenter) implementation that is configuration changes friendly (Supports orientation changes).
* Use of [Repository](http://martinfowler.com/eaaCatalog/repository.html) pattern to access server APIs.

## More about Clean Architecture

TODO...

## More about PresenterHolder

TODO...

## More about Repository pattern

TODO...
