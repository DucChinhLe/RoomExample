package gst.trainingcourse.roomexample.fragment.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import gst.trainingcourse.roomexample.R
import gst.trainingcourse.roomexample.model.User
import kotlinx.android.synthetic.main.custom_row_item.view.*

class ListUserAdapter : RecyclerView.Adapter<ListUserAdapter.MyViewHolder>() {
    private var listUser = emptyList<User>()

    class MyViewHolder(item: View) : RecyclerView.ViewHolder(item)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.custom_row_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = listUser[position]
        holder.itemView.txtID.text = currentItem.id.toString()
        holder.itemView.txtFirstName.text = currentItem.firstName
        holder.itemView.txtLastName.text = currentItem.lastName
        holder.itemView.txtAge.text = currentItem.age.toString()

        //Click 1 item in list
        holder.itemView.rowLayout.setOnClickListener {
        val actionGoToUpdateFragment  = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(actionGoToUpdateFragment)
        }
    }

    override fun getItemCount(): Int {
        return listUser.size
    }

    fun setData(user: List<User>) {
        this.listUser = user
        notifyDataSetChanged()
    }
}