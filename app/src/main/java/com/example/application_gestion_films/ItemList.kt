package com.example.application_gestion_films

class ItemList {

    var icons:Int ? = null
    var title:String ? = null
    var detail:String ? = null
    var date:String ? = null

    constructor(icons: Int?, title: String?, detail: String?, date: String?) {
        this.icons = icons
        this.title = title
        this.detail = detail
        this.date = date
    }
}