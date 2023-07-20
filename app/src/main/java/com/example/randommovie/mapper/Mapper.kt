package com.example.randommovie.mapper

interface Mapper<I, O> {

    fun map(input: I): O
}