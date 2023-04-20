package com.hikky.baikiemtra2.model

class ProductModel {
    var uid : String =""
    var tenSp: String =""
    var loaiSp: String =""
    var giaSp: String =""
    var urlImageSp: String =""

    constructor()

    constructor(uid: String, tenSp: String, loaiSp: String, giaSp: String, urlImageSp: String) {
        this.uid = uid
        this.tenSp = tenSp
        this.loaiSp = loaiSp
        this.giaSp = giaSp
        this.urlImageSp = urlImageSp
    }

    constructor(tenSp: String, loaiSp: String, giaSp: String, urlImageSp: String) {
        this.tenSp = tenSp
        this.loaiSp = loaiSp
        this.giaSp = giaSp
        this.urlImageSp = urlImageSp
    }


}