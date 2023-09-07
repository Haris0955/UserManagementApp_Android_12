package com.example.usermanagementapp

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), UserManagement {

    private val arrUser = ArrayList<UserItem>()
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        arrUser.add(UserItem(1, "Haris Reyaz Wani", "Adventurer seeking new challenges and experiences. Passionate about exploring the world and learning from different cultures.", R.drawable.a, true))
        arrUser.add(UserItem(2, "John Doe 1", "Tech enthusiast and lifelong learner. Always curious about the latest innovations and eager to solve complex problems.", R.drawable.a, true))
        arrUser.add(UserItem(3, "Jane Smith 2", "Nature lover and outdoor enthusiast. Hiking, camping, and capturing the beauty of the wilderness through photography are my passions.", R.drawable.b, true))
        arrUser.add(UserItem(4, "Michael Johnson 3", "Foodie and aspiring chef. Experimenting with flavors and creating culinary masterpieces is what I love to do in my free time.", R.drawable.c, true))
        arrUser.add(UserItem(5, "Emily Williams 4", "Fitness fanatic and marathon runner. Committed to leading a healthy lifestyle and motivating others to stay active.", R.drawable.d, true))
        arrUser.add(UserItem(6, "Daniel Brown 5", "Artistic soul with a flair for creativity. Expressing myself through painting, drawing, and crafting brings me joy.", R.drawable.e, true))
        arrUser.add(UserItem(7, "Olivia Jones 6", "Bookworm and wordsmith. Exploring different literary worlds and writing stories is my way of escaping reality.", R.drawable.f, true))
        arrUser.add(UserItem(8, "William Wilson 7", "Humanitarian at heart, dedicated to making a positive impact on the world through volunteering and community service.", R.drawable.g, true))
        arrUser.add(UserItem(9, "Sophia Anderson 8", "Music lover and aspiring musician. Strumming the guitar and composing melodies help me express my emotions.", R.drawable.h, true))
        arrUser.add(UserItem(10, "Christopher Martin 9", "Entrepreneur with a passion for startups. Dreaming big and working hard to turn innovative ideas into successful businesses.", R.drawable.a, true))

        recyclerView = findViewById(R.id.idUserRV)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = UserAdapter(arrUser, this)
    }

    override fun add(id: Int) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_user, null)
        val editTextName = dialogView.findViewById<EditText>(R.id.editTextName)
        val editTextBio = dialogView.findViewById<EditText>(R.id.editTextBio)

        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setTitle("Add User")
        dialogBuilder.setView(dialogView)
        dialogBuilder.setPositiveButton("Add") { _, _ ->
            val name = editTextName.text.toString()
            val bio = editTextBio.text.toString()

            val randomChar = ('a'..'h').random()

            val randomPictureResource = when (randomChar) {
                'a' -> R.drawable.a
                'b' -> R.drawable.b
                'c' -> R.drawable.c
                'd' -> R.drawable.d
                'e' -> R.drawable.e
                'f' -> R.drawable.f
                'g' -> R.drawable.g

                else -> R.drawable.h
            }
            val newUser = UserItem(arrUser.size + 1, name, bio, randomPictureResource, true)
            arrUser.add(newUser)
            recyclerView.adapter?.notifyDataSetChanged()
        }
        dialogBuilder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }
        val dialog = dialogBuilder.create()
        dialog.show()
    }

    override fun delete(id: Int) {
        val userToDelete = arrUser.find { it.id == id }

        if (userToDelete != null) {
            val dialogBuilder = AlertDialog.Builder(this)
            dialogBuilder.setTitle("Delete User")
            dialogBuilder.setMessage("Are you sure you want to delete ${userToDelete.userName}?")
            dialogBuilder.setPositiveButton("Delete") { _, _ ->
                arrUser.remove(userToDelete)
                recyclerView.adapter?.notifyDataSetChanged()
            }
            dialogBuilder.setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            val dialog = dialogBuilder.create()
            dialog.show()
        }
    }
}
