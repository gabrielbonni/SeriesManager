package br.edu.ifsp.scl.ads.pdm.seriesmanager

import android.content.Intent
import android.os.Bundle
<<<<<<< HEAD
import android.view.Menu
=======
>>>>>>> 4505eb7479f9a49480b258f4df4e9d01ca3e9808
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.edu.ifsp.scl.ads.pdm.seriesmanager.adapter.SeriesRvAdapter
import br.edu.ifsp.scl.ads.pdm.seriesmanager.controller.SerieController
import br.edu.ifsp.scl.ads.pdm.seriesmanager.databinding.ActivitySerieListaBinding
import br.edu.ifsp.scl.ads.pdm.seriesmanager.model.serie.Serie
import com.google.android.material.snackbar.Snackbar

class SerieListagemActivity : AppCompatActivity(), OnSerieClickListener {
    companion object Extras {
        const val EXTRA_SERIE = "EXTRA_SERIE"
        const val EXTRA_SERIE_POSICAO = "EXTRA_POSICAO_SERIE"
    }

    private val activityMainBinding: ActivitySerieListaBinding by lazy {
        ActivitySerieListaBinding.inflate(layoutInflater)
    }

    private lateinit var serieActivityResultLauncher: ActivityResultLauncher<Intent>
    private lateinit var visualizarSerieActivityResultLauncher: ActivityResultLauncher<Intent>

    //Controller
    private val serieController: SerieController by lazy {
        SerieController(this)
    }

    //Data source
    private val serieList: MutableList<Serie> by lazy {
        serieController.buscarSeries()
    }

    //Adapter
    private val serieAdapter: SeriesRvAdapter by lazy {
        SeriesRvAdapter(this, serieList)
    }

    //Layout Manager
    private val serieLayoutManager: LinearLayoutManager by lazy {
        LinearLayoutManager(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        activityMainBinding.seriesRv.adapter = serieAdapter
        activityMainBinding.seriesRv.layoutManager = serieLayoutManager

        //Associar Adapter e Layout Manager ao Recycler View
        serieActivityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { resultado ->
            if (resultado.resultCode == RESULT_OK) {
                resultado.data?.getParcelableExtra<Serie>(EXTRA_SERIE)?.apply {
                    serieController.inserirSerie(this)
                    serieList.add(this)
                    serieAdapter.notifyDataSetChanged()
                }
            }
        }

        visualizarSerieActivityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { resultado ->
            if (resultado.resultCode == RESULT_OK) {
                val posicao = resultado.data?.getIntExtra(EXTRA_SERIE_POSICAO, -1)
                resultado.data?.getParcelableExtra<Serie>(EXTRA_SERIE)?.apply {
                }
            }
        }

        activityMainBinding.adcionarSerieFab.setOnClickListener {
            serieActivityResultLauncher.launch(Intent(this, SerieActivity::class.java))
        }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val posicao = serieAdapter.posicao
        val serie = serieList[posicao]

        return when(item.itemId) {
            R.id.visualizarSerieMi -> {
                //Visualizar uma s??rie
                val visualizarSerieIntent = Intent(this, SerieActivity::class.java)
                visualizarSerieIntent.putExtra(EXTRA_SERIE, serie)
                visualizarSerieIntent.putExtra(EXTRA_SERIE_POSICAO, posicao)
                visualizarSerieActivityResultLauncher.launch(visualizarSerieIntent)
                true
            }
            R.id.removerSerieMi -> {
                //Remover uma s??rie
                with(AlertDialog.Builder(this)) {
                    setMessage("Confirma a remo????o?")
                    setPositiveButton("Sim") { _, _ ->
                        serieController.apagarSerie(serie.nomeSerie)
                        serieList.removeAt(posicao)
                        serieAdapter.notifyDataSetChanged()
                        Snackbar.make(activityMainBinding.root, "Serie removida", Snackbar.LENGTH_SHORT).show()
                    }
                    setNegativeButton("N??o") { _, _ ->
                        Snackbar.make(activityMainBinding.root, "Remo????o cancelada", Snackbar.LENGTH_SHORT).show()
                    }
                    create()
                }.show()

                true
            } else -> { false }
        }
    }

<<<<<<< HEAD
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId){
        R.id.atualizarMi -> {
            serieAdapter.notifyDataSetChanged()
            true
        }
        R.id.sairMi -> {
            AutenticacaoFirebase.firebaseAuth.signOut()
            finish()
            true
        }
        else -> {
            false
        }
    }
=======
>>>>>>> 4505eb7479f9a49480b258f4df4e9d01ca3e9808
    override fun onSerieClick(posicao: Int) {
        val serie = serieList[posicao]
        val consultarTemporadasIntent = Intent(this, TemporadaListagemActivity::class.java)
        consultarTemporadasIntent.putExtra(EXTRA_SERIE, serie)
        startActivity(consultarTemporadasIntent)
    }
<<<<<<< HEAD

    override fun onStart() {
        super.onStart()
        if(AutenticacaoFirebase.firebaseAuth.currentUser == null){
            finish()
        }
    }
=======
>>>>>>> 4505eb7479f9a49480b258f4df4e9d01ca3e9808
}