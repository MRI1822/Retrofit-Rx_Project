package com.mrigankprojects.myretrofit_rx.adapter

import android.support.v7.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mrigankprojects.myretrofit_rx.R
import com.mrigankprojects.myretrofit_rx.api.response.NewsListResponse
import kotlinx.android.synthetic.main.list_item_news.view.*
import java.text.DateFormat
import java.util.ArrayList


class NewsRecyclerAdapter : RecyclerView.Adapter<NewsRecyclerAdapter.TaskViewHolder>() {

    lateinit var tasks: ArrayList<NewsListResponse.Article>
    lateinit var mItemClickListener: MyAdapterListener
    override fun onCreateViewHolder(parent: android.view.ViewGroup, type: Int): TaskViewHolder {
        return TaskViewHolder(parent)
    }

    fun setData(tasks: ArrayList<NewsListResponse.Article>) {
        this.tasks = tasks
        notifyDataSetChanged()
    }

    fun addTodo(todoTask: NewsListResponse.Article) {
        this.tasks.add(todoTask)
        notifyItemChanged(tasks.size)
    }

    override fun onBindViewHolder(viewHolder: NewsRecyclerAdapter.TaskViewHolder, position: Int) {
        viewHolder.bind(tasks[position], position)
    }

    override fun getItemCount(): Int = tasks.size

    inner class TaskViewHolder(parent: android.view.ViewGroup) :
        RecyclerView.ViewHolder(android.view.LayoutInflater.from(parent.context).
            inflate(R.layout.list_item_news, parent, false)) {

        fun bind(task: NewsListResponse.Article, position: Int): Unit = with(itemView) {
            tvListItemAuthor.text=task.author
            tvListItemDateTime.text=task.publishedAt
            tvListItemTitle.text=task.title
            itemView.setOnClickListener({
                task.url?.let { mUrl -> mItemClickListener.onItemViewClick(mUrl,position) }
            })
            Glide.with(itemView.context)
                .load(task.urlToImage)
                .into(ivListItem)
            //2017-07-14T16:54:13Z
            var df: DateFormat = java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", java.util.Locale.getDefault())
            val d = df.parse(task.publishedAt)
            df = java.text.SimpleDateFormat("dd MMM,yyyy hh:mm a", java.util.Locale.getDefault())
            tvListItemDateTime.text=df.format(d)
        }
    }


    fun setOnItemClick(itemClickListener: MyAdapterListener): Unit {
        this.mItemClickListener = itemClickListener
    }

    interface MyAdapterListener {
        fun onItemViewClick(webUrl:String,position: Int)
    }
}