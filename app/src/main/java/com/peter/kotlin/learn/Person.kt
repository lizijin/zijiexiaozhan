package com.peter.kotlin.learn

class Person(val firstName: String, val lastName: String, var age: Int) {
    var stringRepresentation: String
        get() = this.toString()
        set(value) {

        }
}