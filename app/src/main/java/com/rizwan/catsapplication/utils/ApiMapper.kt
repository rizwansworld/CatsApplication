package com.rizwan.catsapplication.utils

/**
 * This interface is used to map an API model to a Domain model
 *
 * @param <I> represents the input type
 * @param <O> represents the output type
</O></I> */
interface ApiMapper<I, O> {
    /**
     * @param input represents the API model
     * @return the Domain model
     */
    fun mapToDomain(input: I): O
}