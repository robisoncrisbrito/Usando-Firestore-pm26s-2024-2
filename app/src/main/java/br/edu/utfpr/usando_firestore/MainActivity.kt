package br.edu.utfpr.usando_firestore

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class MainActivity : AppCompatActivity() {

    private lateinit var etCod : EditText
    private lateinit var etNome : EditText
    private lateinit var etTelefone : EditText

    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etCod = findViewById( R.id.etCod )
        etNome = findViewById( R.id.etNome )
        etTelefone = findViewById( R.id.etTelefone )

    }

    fun btIncluirOnClick(view: View) {

        val pessoa = hashMapOf (
            "nome" to etNome.text.toString(),
            "telefone" to etTelefone.text.toString()
        )

        db.collection( "Pessoa" )
            .document( etCod.text.toString() )
            .set( pessoa )
            .addOnSuccessListener {
                Toast.makeText( this, "Sucesso", Toast.LENGTH_LONG ).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText( this, "Erro: ${e}", Toast.LENGTH_LONG ).show()
            }

    }
    fun btAlterarOnClick(view: View) {

        val pessoa = hashMapOf (
            "nome" to etNome.text.toString(),
            "telefone" to etTelefone.text.toString()
        )

        db.collection( "Pessoa" )
            .document( etCod.text.toString() )
            .set( pessoa )
            .addOnSuccessListener {
                Toast.makeText( this, "Sucesso", Toast.LENGTH_LONG ).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText( this, "Erro: ${e}", Toast.LENGTH_LONG ).show()
            }
    }
    fun btExcluirOnClick(view: View) {

        db.collection( "Pessoa" )
            .document( etCod.text.toString() )
            .delete()
            .addOnSuccessListener {
                Toast.makeText( this, "Sucesso", Toast.LENGTH_LONG ).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText( this, "Erro: ${e}", Toast.LENGTH_LONG ).show()
            }


    }
    fun btPesquisarOnClick(view: View) {

        db.collection( "Pessoa" )
            .whereEqualTo( "nome", etNome.text.toString() )
            .get()
            .addOnSuccessListener { result ->
                val registro = result.elementAt( 0 ).data

                etNome.setText( registro.get( "nome" ).toString() )
                etTelefone.setText( registro.get( "telefone" ).toString() )
=
                Toast.makeText( this, "Sucesso", Toast.LENGTH_LONG ).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText( this, "Erro: ${e}", Toast.LENGTH_LONG ).show()
            }


    }
    fun btListarOnClick(view: View) {

        db.collection( "Pessoa" )
            .get()
            .addOnSuccessListener { result ->
                val saida = StringBuilder()
                for ( document in result ) {
                    saida.append( document.data.get( "nome" ) )
                    saida.append( " - " )
                    saida.append( document.data.get( "telefone" ) )
                    saida.append( "\n" )
                }
                Toast.makeText( this, saida, Toast.LENGTH_LONG ).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText( this, "Erro: ${e}", Toast.LENGTH_LONG ).show()
            }
    }
}