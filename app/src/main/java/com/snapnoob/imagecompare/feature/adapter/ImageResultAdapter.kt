package com.snapnoob.imagecompare.feature.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.docotel.libs.base.BaseAppAdapter
import com.docotel.libs.base.BaseViewHolder
import com.snapnoob.imagecompare.BuildConfig
import com.snapnoob.imagecompare.R
import com.snapnoob.imagecompare.data.model.ResultData
import kotlinx.android.synthetic.main.list_result_compare.view.*

/**
 * Created by @ImamKR97
 **/
class ImageResultAdapter(private val dataItem: MutableList<ResultData>) : BaseAppAdapter() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder =
        ImageResultViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_result_compare, parent, false))

    fun addItem(resultItem: List<ResultData>) {
        dataItem.addAll(resultItem)
        notifyDataSetChanged()
    }

    fun clearItem() {
        dataItem.clear()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = dataItem.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.clear()
        holder.onBind(position)
    }

    inner class ImageResultViewHolder(itemView: View) : BaseViewHolder(itemView) {
        override fun clear() {
            itemView.tv_compare_with.text = null
            itemView.tv_result_similarity.text = null
        }

        override fun onBind(postition: Int) {
            inflateData(dataItem[postition])
        }

        private fun inflateData(resultData: ResultData) {
            itemView.tv_compare_with.text = "Compared with : ${resultData.compared_with}"
            itemView.tv_result_similarity.text = "Similarity (%) : ${resultData.similarity}"

            Glide.with(itemView.context)
                .load(BuildConfig.BASE_URL + resultData.img_url)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(itemView.img_result)

        }
    }
}