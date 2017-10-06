package com.example.yanal.assessmentsegurancayanalves;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yanal.assessmentsegurancayanalves.Domain.Usuario;
import com.example.yanal.assessmentsegurancayanalves.Utils.FileUtil;
import com.example.yanal.assessmentsegurancayanalves.Utils.TextValidator;
import com.example.yanal.assessmentsegurancayanalves.Utils.ValidationUtil;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private FileUtil _fileUtil = new FileUtil();
    private EditText txtBoxConfirmaSenha;
    private EditText txtBoxEmail;
    private EditText txtBoxNome;
    private EditText txtBoxSenha;
    private EditText txtBoxCpf;

    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        txtBoxConfirmaSenha = (EditText)findViewById(R.id.txtBoxConfirmarSenha);
        txtBoxEmail = (EditText)findViewById(R.id.txtBoxEmail);
        txtBoxNome = (EditText)findViewById(R.id.txtBoxNome);
        txtBoxSenha = (EditText)findViewById(R.id.txtBoxSenha);
        txtBoxCpf = (EditText)findViewById(R.id.txtBoxCpf);

        txtBoxNome.addTextChangedListener(new TextValidator(txtBoxNome) {
            @Override
            public void validate(EditText editText, String text) {
                String nome = txtBoxNome.getText().toString();
                if (nome.isEmpty())
                {
                    txtBoxNome.setError("Nome é obrigatório");
                }

                if(ValidationUtil.TemCaracteresEspeciais(nome))
                {
                    txtBoxNome.setError("Nome não pode conter caracteres especiais");
                }
            }
        });

        txtBoxEmail.addTextChangedListener(new TextValidator(txtBoxEmail) {
            @Override
            public void validate(EditText editText, String text) {
                String email = txtBoxEmail.getText().toString();
                if (email.isEmpty())
                {
                    txtBoxEmail.setError("E-mail é obrigatório");
                }

                if(!ValidationUtil.EhEmailValido(email))
                {
                    txtBoxEmail.setError("E-mail inválido");
                }
            }
        });

        txtBoxConfirmaSenha.addTextChangedListener(new TextValidator(txtBoxConfirmaSenha) {
            @Override
            public void validate(EditText editText, String text) {
                String confirmaSenha = txtBoxConfirmaSenha.getText().toString();
                if (confirmaSenha.isEmpty())
                {
                    txtBoxConfirmaSenha.setError("Confirme sua senha");
                }

                if (!Objects.equals(confirmaSenha, txtBoxSenha.getText().toString()))
                {
                    txtBoxConfirmaSenha.setError("As senhas devem ser iguais");
                }
            }
        });

        txtBoxCpf.addTextChangedListener(new TextValidator(txtBoxCpf) {
            @Override
            public void validate(EditText editText, String text) {
                String cpf = txtBoxCpf.getText().toString();
                if (cpf.isEmpty())
                {
                    txtBoxCpf.setError("CPF é obrigatório");
                }

                if (!ValidationUtil.EhCpfValido(cpf))
                {
                    txtBoxCpf.setError("CPF inválido");
                }
            }
        });

        txtBoxSenha.addTextChangedListener(new TextValidator(txtBoxSenha) {
            @Override
            public void validate(EditText editText, String text) {
                if (txtBoxSenha.getText().toString().isEmpty())
                {
                    txtBoxSenha.setError("Senha é obrigatória");
                }
            }
        });
    }

    public void LimparFormulario(View view)
    {
        List<EditText> campos = new ArrayList<EditText>();

        campos.add(txtBoxConfirmaSenha);
        campos.add(txtBoxEmail);
        campos.add(txtBoxNome);
        campos.add(txtBoxSenha);
        campos.add(txtBoxCpf);

        LimparCampos(campos);
    }

    public void SalvarFormulario(View view)
    {
        Usuario usuario = new Usuario();
        String email = txtBoxEmail.getText().toString();
        String nome = txtBoxNome.getText().toString();
        String senha = txtBoxSenha.getText().toString();
        String cpf =  txtBoxCpf.getText().toString();

        if (!FormularioEhValido())
        {
            Toast("Formulário inválido");

            return;
        }

        usuario.setSenha(senha);
        usuario.setEmail(email);
        usuario.setNome(nome);
        usuario.setCpf(cpf);

        SalvarUsuario(usuario);

        LimparFormulario(view);

        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.");
        }

        Toast("Usuário salvo com sucesso");

    }

    private void SalvarUsuario(Usuario usuario) {
        String usuarioSerializado = usuario.getNome() + "," +
                                    usuario.getSenha() + "," +
                                    usuario.getEmail() + "," +
                                    usuario.getCpf() + "-";

        _fileUtil.EscreverLinha(usuarioSerializado, getBaseContext());
    }

    private boolean FormularioEhValido()
    {
        if (txtBoxConfirmaSenha.getText().toString().isEmpty())
        {
            txtBoxConfirmaSenha.setError("Confirme sua senha");
        }

        String nome = txtBoxNome.getText().toString();
        if (nome.isEmpty())
        {
            txtBoxNome.setError("Nome é obrigatório");
        }

        String email = txtBoxEmail.getText().toString();
        if (email.isEmpty())
        {
            txtBoxEmail.setError("E-mail é obrigatório");
        }

        String cpf = txtBoxCpf.getText().toString();
        if (cpf.isEmpty())
        {
            txtBoxCpf.setError("CPF é obrigatório");
        }

        if (txtBoxSenha.getText().toString().isEmpty())
        {
            txtBoxSenha.setError("Senha é obrigatória");
        }

        return txtBoxNome.getError() == null &&
               txtBoxConfirmaSenha.getError() == null &&
               txtBoxCpf.getError() == null &&
               txtBoxEmail.getError() == null &&
               txtBoxSenha.getError() == null;
    }

    private void LimparCampos(List<EditText> campos)
    {
        for (EditText campo: campos) {
            campo.setText("");
            campo.setError(null);
        }
    }

    private void Toast(String mensagem)
    {
        Context contexto = getApplicationContext();
        String texto = mensagem;
        int duracao = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(contexto, texto,duracao);
        toast.show();
    }
}
