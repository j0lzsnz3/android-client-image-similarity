package com.snapnoob.imagecompare.feature.main

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.docotel.libs.base.view.BaseActivity
import com.snapnoob.imagecompare.R
import com.snapnoob.imagecompare.data.model.ResultResponse
import com.snapnoob.imagecompare.feature.adapter.ImageResultAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import android.graphics.Bitmap
import android.util.Base64
import java.io.ByteArrayOutputStream
import android.graphics.BitmapFactory




class MainActivity : BaseActivity(), MainContract.View {

    private val presenter: MainPresenter<MainContract.View> by inject()
    private val adapter: ImageResultAdapter by inject()

    private val REQUEST_CODE_BROWSER = 140

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.onAttach(this)

        btn_browse_image.setOnClickListener {
            val intent = Intent().setType("*/*").setAction(Intent.ACTION_GET_CONTENT)
            startActivityForResult(Intent.createChooser(intent, "Select PNG File"), REQUEST_CODE_BROWSER)
        }
    }

    override fun onFragmentAttached() {
    }

    override fun onFragmentDettached(tag: String) {
    }

    override fun showResultCompare(resultData: ResultResponse) {
        adapter.clearItem()
        adapter.addItem(resultData.result)
        rv_compare_result.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.VERTICAL, false)
        rv_compare_result.setHasFixedSize(false)
        rv_compare_result.adapter = adapter
    }

    private fun compareImage(imageEncode: String) {
        adapter.clearItem()
        presenter.doCompareImages(imageEncode)
    }

    override fun showError(errorMessage: String?) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_BROWSER && resultCode == RESULT_OK) {
            val selectedFile = data?.data //The uri with the location of the file
            if (selectedFile != null) {
                val imageStream = contentResolver.openInputStream(selectedFile)
                val selectedImage = BitmapFactory.decodeStream(imageStream)
                val imageEncode = encodeImage(selectedImage)
                compareImage(imageEncode)
            }
        }
    }

    private fun encodeImage(bm: Bitmap): String {
        val baos = ByteArrayOutputStream()
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val b = baos.toByteArray()

        return Base64.encodeToString(b, Base64.DEFAULT)
    }

}
