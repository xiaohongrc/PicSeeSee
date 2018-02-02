package com.example.hongenit.testapplication

/**
 * Created by hongenit on 18/1/19.
 *
 */


 data class User(var name:String, var id:Int){

 override fun equals(other: Any?): Boolean {
  return super.equals(other)
 }

}