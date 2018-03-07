package ru.reo7sp.tprk1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ru.mail.park.articlelistlib.Article;


public class ArticleFragment extends Fragment {
    private static final String ARG_ARTICLE = "article";

    private Article _article;

    public ArticleFragment() {
    }

    public static ArticleFragment newInstance(Article article) {
        ArticleFragment fragment = new ArticleFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_ARTICLE, article);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            _article = (Article) getArguments().getSerializable(ARG_ARTICLE);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (_article == null) {
            return;
        }

        TextView titleView = view.findViewById(R.id.article_title);
        TextView dateView = view.findViewById(R.id.article_date);
        TextView contentsView = view.findViewById(R.id.article_contents);

        titleView.setText(_article.getTitle());
        dateView.setText(_article.getDate().toString());
        contentsView.setText(_article.getContent());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_article, container, false);
    }
}
