package goodline.info.cardbag;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;
import java.util.List;

public class ImageCardAdapter extends RecyclerView.Adapter<ImageCardVH> {

    private List<Photo> cards;
    private LayoutInflater inflater;
    Context context;

    public ImageCardAdapter(Context context, List<Photo> cardList) {
        this.cards = cardList;
        this.inflater = LayoutInflater.from(context);
        this.context=context;
    }

    @NonNull
    @Override
    public ImageCardVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.item_view_card, viewGroup, false);
        return new ImageCardVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageCardVH imageCardVH, int position) {
        File imgFile = new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES) + "/" +
                        cards.get(position).getImageID() + ".jpg"
        );

        if (imgFile.exists()) {
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            imageCardVH.imageFront.setImageBitmap(myBitmap);
        }
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }
}
