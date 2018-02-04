package com.example.hongenit.testapplication

/**
 * Created by hongenit on 18/1/18.
 *
 */





class TestMain {
    fun main() {
//        print(getStringLength("33noint"))
//        printOneByOne(arrayOf("s1", "s2", "s3"))
//        forLoopStrings(arrayOf("s1", "s2", "s3"))
//        testRnage(5.022f)

//        cases("sd")
//        cases(3.3f)
//        cases("str1")
//        userData()
//        traverseMap()
//        copyFun()
//        checkNull()
//        testExtends()

//        // 抽象类不能被创建，必须前前加object:
//        absClassAndInnerClass(object :AbsClass(){
//            override fun absFun() {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//            }
//        })

        testLet()




    }



    // 使用？.let关键字，闭包函数中的it可代表该对象。当该对象为空时，闭包函数不执行。
    fun testLet(){
        var student: Student?  = null
        var person: Person = Student()

        student?.let {
            println("sssss")
            println(it.soutName())

        }?: person.let {
            println("pppp")
            it.soutName()
        }

    }



    // 抽象类不能被创建，必须前前加object:
    fun absClassAndInnerClass(abslass: AbsClass){
        abslass.absFun()
    }
    // 与Java一样，有抽象方法必须是抽象类
    abstract class AbsClass{
        abstract fun absFun()
    }

    // kotlin继承时被继承的父类必须是open，没有open修饰的函数不可以被重写，但是可以被继承和使用。
    fun testExtends(){
        var  student =  Student()
        var  person :Person?
        person = student
        person.soutName()

    }


    // ?符号表示可以为空，!!.代表断言不为空，为空则抛空指针异常
    // 如果不加？符号，则必须初始化不为空的值，或者加上关键字lateinit
    fun checkNull() {

        var user: User? = User("", 9)
        user?.id = 10000
        user = null
        println("print ${user?.id}")
        user.toString()

        user?.id = 10000
        println("print2222 ${user?.name.equals(null)}")

    }


    //    copy函数
    fun copyFun() {
        val user = User("xiaohong", 1)
        val user1 = User("xiaohong", 1)
        val user2 = User("xiaoming", 2)

        //user 和 user1两个对象的hashcode值是一样的，所以相等或者说equals（）返回true
        println("user ${user.hashCode()} == user1 ${user1.hashCode()} : ${user == user1}")
        println("user == user1 : ${user.equals(user1)}")
        println("user == user1 : ${user == user2}")

        println(user.copy())
        val user3 = user.copy("xiaoli")
        println(user3)
        println(user.copy(name = "xiaoli", id = 3))
        println(user.copy(id = 2))
    }

    // for遍历一个hashMap集合
    fun traverseMap() {
        val hashMap = hashMapOf<String, Int>()
        hashMap.put("key1", 1)
        hashMap.put("key2", 2)
        hashMap.put("key3", 3)

        for ((key, value) in hashMap) {
            println("key = $key, value = $value")
        }
    }

    // 获取data类属性的几种方式
    private fun userData() {
        val user = User("xiaohong", 1988)
//        println("name = ${user.name}   id = ${user.id}")

        val (name, id) = user
        println("name = $name   id = $id")
        println("name = ${user.component1()}   id = ${user.component2()}")


    }


    // when 可以匹配任何类型（即Any类型），还可匹配类型判断表达式。
    // Any为任何类型，kotlin所有类型的超类。如java的Object类
    // 函数默认的返回值为Unit，如Java的void。
    fun cases(obj: Any?) {
        when (obj) {
            1 -> println(obj)
            3.3f -> println("3.3f")
            is Float -> println("is float  = ${obj}")
            !is String -> println("not string")
            "str1" -> println("= str1")
            else -> println("else")
        }
    }


    // in既可用于for遍历，也可判断是否包含在某个范围
    fun testRnage(a: Float) {
        val y = 10
        // a 是否在[1.0，5.2]范围内。
        if (a in 1.0f..5.2f) {
            println("属于个位数")
        } else {
            println("bushuyu")
        }
        for (i in 1..5) {
            println("${i} = " + i)
        }
        val strings = arrayOf("s1", "s2", "s3")
        if ("s4" in strings) {
            println("shi")
        } else {
            println("fou")
        }
    }


    // 增强for循环 通过in来遍历数组
    fun forLoopStrings(strs: Array<String>) {
//        for (str in strs){
//            println(str)
//        }

        // 或者通过index来遍历。
        for (i in strs.indices) {
            println("strs[${i}] = ${strs[i]}")
        }
    }

    //    while循环的写法，注意var表示变量，val表示值。
    fun printOneByOne(strs: Array<String>) {
        var i = 0
//        val i = 0
        while (i < strs.size) {
            println(strs[i++])
        }
    }

    // is判断类型，判断语句之后可智能转换。
    fun getStringLength(obj: Any): Int? {
        if (obj is String)
            return obj.length
        return null
    }

    // 空检查，？代表可以为空
    fun parseInt(str: String): Int? {
        try {
            return str.toInt()
        } catch (e: NumberFormatException) {
            println("your input is not a Int number")
        }
        return null
    }

}

class Pair<K, V>
