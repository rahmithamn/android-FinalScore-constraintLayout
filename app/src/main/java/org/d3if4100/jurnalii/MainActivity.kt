package org.d3if4100.jurnalii

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import android.widget.Toast.*
import androidx.databinding.DataBindingUtil
import org.d3if4100.jurnalii.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    @SuppressLint("StringFormatInvalid")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.apply {
            tvNilaiakhir.text = ""
            tvIndeks.text = ""
            imageMood.setImageResource(0)
            etNama.requestFocus()
            rbYa.isChecked = true
            btnHitungnilai.setOnClickListener{
                if (!TextUtils.isEmpty(etNama.text.toString()) &&
                    !TextUtils.isEmpty(etNilaipraktikum.text.toString()) &&
                    !TextUtils.isEmpty(etAssessment1.text.toString()) &&
                    !TextUtils.isEmpty(etAssessment2.text.toString()) &&
                        isInRange(etNilaipraktikum.text.toString().toFloat()) &&
                        isInRange(etAssessment1.text.toString().toFloat()) &&
                        isInRange(etAssessment2.text.toString().toFloat())) {

                        var nilai_akhir = ((3 * etNilaipraktikum.text.toString().toFloat()) / 10) + ((3 * etAssessment1.text.toString().toFloat()) / 10)

                        if (rbYa.isChecked) {
                             nilai_akhir += ((4 * etNilaipraktikum.text.toString().toFloat()) / 10) + ((3 * etAssessment1.text.toString().toFloat()) / 10)
                                           + ((3 * etAssessment2.text.toString().toFloat()) / 10)
                         }

                        tvNilaiakhir.text = "Nilai Akhir : $nilai_akhir"

                        if(nilai_akhir > 50) {
                            imageMood.setImageResource(R.drawable.ic_mood_blue_24dp)
                            tvIndeks.setTextColor(Color.parseColor("#FF3F51B5"))
                        } else {
                            imageMood.setImageResource(R.drawable.ic_mood_bad_pink_24dp)
                            tvIndeks.setTextColor(Color.parseColor("#FFFF4081"))
                        }

                        if (nilai_akhir <= 40) {
                            tvIndeks.text = "E"
                        } else if(nilai_akhir <= 50) {
                            tvIndeks.text = "D"
                        } else if(nilai_akhir <= 60) {
                            tvIndeks.text = "C"
                        } else if(nilai_akhir <= 65) {
                            tvIndeks.text = "BC"
                        } else if(nilai_akhir <= 70) {
                            tvIndeks.text = "B"
                        } else if (nilai_akhir <= 80) {
                            tvIndeks.text = "AB"
                        } else if(nilai_akhir <= 100) {
                            tvIndeks.text = "A"
                        }

                } else {
                    Toast.makeText(applicationContext, "Isian Tidak Valid", Toast.LENGTH_SHORT).show()
                }
            }
            btnReset.setOnClickListener{
                etNama.text.clear()
                etNilaipraktikum.text.clear()
                etAssessment1.text.clear()
                etAssessment2.text.clear()
                tvNilaiakhir.text = ""
                tvIndeks.text = ""
                imageMood.setImageResource(0)
                etNama.requestFocus()
                rbYa.isChecked = true
            }
        }

    }

    private fun isInRange(a:Float): Boolean {
        return a >= 0 && a <= 100
    }
}
