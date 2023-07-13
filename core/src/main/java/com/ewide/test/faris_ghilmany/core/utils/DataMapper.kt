package com.ewide.test.faris_ghilmany.core.utils

import com.ewide.test.faris_ghilmany.core.data.source.local.entity.DetailGameEntity
import com.ewide.test.faris_ghilmany.core.data.source.local.entity.GameEntity
import com.ewide.test.faris_ghilmany.core.data.source.remote.response.DetailGameResponse
import com.ewide.test.faris_ghilmany.core.data.source.remote.response.ListGameResponse
import com.ewide.test.faris_ghilmany.core.domain.model.DetailGame

object DataMapper {
    fun mappingListGameResponseToGameEntity(list: List<ListGameResponse>?): List<GameEntity>? = list?.map {
        with(it){
            GameEntity(gameID.toString(), title, normalPrice, thumb, dealRating,)
        }
    }

    fun mappingGameToGameEntity(game: DetailGame): DetailGameEntity = with(game){
        DetailGameEntity(
            gameID, metacriticScore, salePrice, releaseDate, thumb, steamRatingCount, steamworks, metacriticLink, storeID, steamAppID, steamRatingPercent, name, publisher, retailPrice, steamRatingText, favorite
        )
    }

    fun mappingListGameEntityToListDetailGame(listGame: List<DetailGameEntity>): List<DetailGame> =
        listGame.map {
            with(it){
                DetailGame(
                    gameID, metacriticScore, salePrice, releaseDate, thumb, steamRatingCount, steamworks, metacriticLink, storeID, steamAppID, steamRatingPercent, name, publisher, retailPrice, steamRatingText, favorite
                )
            }
        }

    fun mappingGameEntityToDetailGame(gameEntity: DetailGameEntity): DetailGame = with(gameEntity){
        DetailGame(
            gameID, metacriticScore, salePrice, releaseDate, thumb, steamRatingCount, steamworks, metacriticLink, storeID, steamAppID, steamRatingPercent, name, publisher, retailPrice, steamRatingText, favorite
        )
    }

    fun mappingDetailGameResponseToDetailGameEntity(gameResponse: DetailGameResponse): DetailGameEntity? =
        gameResponse.gameInfo?.let{
            with(it){
                DetailGameEntity(
                    gameID?:"", metacriticScore, salePrice, releaseDate, thumb, steamRatingCount, steamworks, metacriticLink, storeID, steamAppID, steamRatingPercent, name, publisher, retailPrice, steamRatingText, false
                )
            }
        }
}