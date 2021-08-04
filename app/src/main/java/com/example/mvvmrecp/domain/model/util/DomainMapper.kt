package com.example.mvvmrecp.domain.model.util

interface DomainMapper<T,DomainModel> {
    fun mapToDomainModel(model: T):DomainModel
    fun mapFromDomainModel(domainModel: DomainModel):T
}