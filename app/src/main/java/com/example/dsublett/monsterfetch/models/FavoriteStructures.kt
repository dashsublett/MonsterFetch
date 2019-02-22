package com.example.dsublett.monsterfetch.models

data class FavoritesList(
    private val monsterFavorites: List<ResponseItem>,
    private val classFavorites: List<ResponseItem>,
    private val spellFavorites: List<ResponseItem>
) {
    fun flatten(): List<FavoriteItem> {
        val retList = mutableListOf(FavoriteItem(FavoriteType.Header, "MONSTERS", null))
        when {
            this.monsterFavorites.isEmpty() -> retList.add(
                FavoriteItem(FavoriteType.None, "You don't have any monster favorites", null)
            )
            else -> for (item in this.monsterFavorites) {
                retList.add(FavoriteItem(FavoriteType.Favorite, item.name, item))
            }
        }

        retList.add(FavoriteItem(FavoriteType.Header, "CLASSES", null))
        when {
            this.classFavorites.isEmpty() -> retList.add(
                FavoriteItem(FavoriteType.None, "You don't have any class favorites", null)
            )
            else -> for (item in this.classFavorites) {
                retList.add(FavoriteItem(FavoriteType.Favorite, item.name, item))
            }
        }
        retList.add(FavoriteItem(FavoriteType.Header, "SPELLS", null))
        when {
            this.spellFavorites.isEmpty() -> retList.add(
                FavoriteItem(FavoriteType.None, "You don't have any spell favorites", null)
            )
            else -> for (item in this.spellFavorites) {
                retList.add(FavoriteItem(FavoriteType.Favorite, item.name, item))
            }
        }
        return retList
    }
}

data class FavoriteItem(
    val type: FavoriteType,
    val label: String,
    val data: ResponseItem?
)

enum class FavoriteType {
    Header, None, Favorite
}
