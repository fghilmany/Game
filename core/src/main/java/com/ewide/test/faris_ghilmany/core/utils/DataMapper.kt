package com.ewide.test.faris_ghilmany.core.utils

import androidx.paging.PagingData
import androidx.paging.map
import com.ewide.test.faris_ghilmany.core.data.source.local.entity.DetailGameEntity
import com.ewide.test.faris_ghilmany.core.data.source.local.entity.GameEntity
import com.ewide.test.faris_ghilmany.core.data.source.remote.response.DetailGameResponse
import com.ewide.test.faris_ghilmany.core.data.source.remote.response.ListGameResponse
import com.ewide.test.faris_ghilmany.core.domain.model.DetailGame
import com.ewide.test.faris_ghilmany.core.domain.model.Game
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

object DataMapper {
    fun mappingListGameResponseToGameEntity(list: List<ListGameResponse>?): List<GameEntity>? = list?.map {
        with(it){
            GameEntity(dealID.toString(), title, normalPrice, thumb, dealRating, dealID)
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

    fun mappingGameEntityToDetailGame(gameEntity: DetailGameEntity?): DetailGame? {
        if (gameEntity != null) {
            return with(gameEntity) {
                DetailGame(
                    gameID,
                    metacriticScore,
                    salePrice,
                    releaseDate,
                    thumb,
                    steamRatingCount,
                    steamworks,
                    metacriticLink,
                    storeID,
                    steamAppID,
                    steamRatingPercent,
                    name,
                    publisher,
                    retailPrice,
                    steamRatingText,
                    favorite
                )
            }
        }else{
            return null
        }
    }

    fun mappingDetailGameResponseToDetailGameEntity(
        gameResponse: DetailGameResponse,
        gameIdFromDeal: String
    ): DetailGameEntity? =
        gameResponse.gameInfo?.let{
            with(it){
                DetailGameEntity(
                    gameIdFromDeal, metacriticScore?:"", salePrice?:"", releaseDate?:0, thumb?:"", steamRatingCount?:"", steamworks?:"", metacriticLink?:"", storeID?:"", steamAppID?:"", steamRatingPercent?:"", name?:"", publisher?:"", retailPrice?:"", steamRatingText?:"", false
                )
            }
        }

    fun mappingPagingDataGameEntityToPagingDataGame(stories: Flow<PagingData<GameEntity>>): Flow<PagingData<Game>> {
        return stories.map { pagingData ->
            pagingData.map { game ->
                with(game){
                    Game(gameId, title, normalPrice, thumb, dealRating, dealID, favorite)
                }
            }
        }
    }
}