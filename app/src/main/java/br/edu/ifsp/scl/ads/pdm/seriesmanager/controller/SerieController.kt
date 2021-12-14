package br.edu.ifsp.scl.ads.pdm.seriesmanager.controller

import br.edu.ifsp.scl.ads.pdm.seriesmanager.SerieListagemActivity
import br.edu.ifsp.scl.ads.pdm.seriesmanager.model.serie.Serie
import br.edu.ifsp.scl.ads.pdm.seriesmanager.model.serie.SerieDAO
<<<<<<< HEAD
import br.edu.ifsp.scl.ads.pdm.seriesmanager.model.serie.SerieFirebase
import br.edu.ifsp.scl.ads.pdm.seriesmanager.model.serie.SerieSqlite

class SerieController (seriesListagemActivity: SerieListagemActivity) {
    private val serieDAO: SerieDAO = SerieFirebase()
=======
import br.edu.ifsp.scl.ads.pdm.seriesmanager.model.serie.SerieSqlite

class SerieController (seriesListagemActivity: SerieListagemActivity) {
    private val serieDAO: SerieDAO = SerieSqlite(seriesListagemActivity)
>>>>>>> 4505eb7479f9a49480b258f4df4e9d01ca3e9808

    fun inserirSerie(serie: Serie) = serieDAO.criarSerie(serie)
    fun buscarSeries() = serieDAO.recuperarSeries()
    fun apagarSerie(nome: String) = serieDAO.removerSerie(nome)

}