package ru.reo7sp.tprk1;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import ru.mail.park.articlelistlib.Article;
import ru.mail.park.articlelistlib.ArticleListFragment;
import ru.mail.park.articlelistlib.OnArticleClickListener;

public class MainActivity extends AppCompatActivity implements OnArticleClickListener {
    private static final String ARG_ARTICLE = "article";

    private Article _article;
    private ArticleListFragment _listFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _listFragment = new ArticleListFragment();
        _setupListFragment(_listFragment);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.article_list_container, _listFragment);
        transaction.commit();

        _restoreArticle(savedInstanceState);
    }

    private void _setupListFragment(ArticleListFragment listFragment) {
        listFragment.setOnArticleClickListener(this);
    }

    private void _restoreArticle(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            return;
        }
        _article = (Article) savedInstanceState.getSerializable(ARG_ARTICLE);
    }

    private boolean _isLandscape() {
        return getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (_article != null) {
            outState.putSerializable(ARG_ARTICLE, _article);
        }
    }

    @Override
    public void onArticleClick(Article article) {
        ArticleFragment articleFragment = ArticleFragment.newInstance(article);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (_isLandscape()) {
            if (getSupportFragmentManager().getBackStackEntryCount() != 0) {
                getSupportFragmentManager().popBackStack();
            }
            transaction.replace(R.id.article_list_container, _listFragment);
            transaction.replace(R.id.article_container, articleFragment);
        } else {
            transaction.replace(R.id.article_list_container, articleFragment);
            transaction.addToBackStack(null);
        }
        transaction.commit();

        this._article = article;
    }
}
