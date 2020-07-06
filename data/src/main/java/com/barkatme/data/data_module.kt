package com.barkatme.data

import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module


//override allow other modules to make other providers for definitions in that module
//it can also be applied for definitions
val data_module = module(override = true) {
    // declare single instance for NetRepository class
    // Will match type NetRepository only if without "bind"
    // bind make it matches 2 types: NetRepository & Repository
    single(createdAtStart=true) { NetRepository() } //bind Repository::class

    // declare single instance for Repository implementation
    // Will match type Repository only
    single<Repository> { NetRepository() }
    //or this way -> single { NetRepository() as Repository } (not preferred, as koin docs said)

    // declare factory instance for User class
    factory { User() }
    //you can use injection parameters: parameters that will be injected and used by your definition
    factory(named("parametrized")) { (name: String) -> User(name) }
}

fun testDataModule() {

}

val start_data_koin = {
    startKoin {
        modules(data_module)
    }
}