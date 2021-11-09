package shyam.gunsariya.knobee.model

class DummyModel{
    var gallery: String? = null
    var positionNo: Int? = 0

    fun setPosts(number : Int, gallery : String){
        this.gallery = gallery
        this.positionNo = number
    }
}