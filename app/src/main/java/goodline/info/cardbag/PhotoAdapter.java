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

public class PhotoAdapter extends RecyclerView.Adapter<PhotoViewHolder>{
    private LayoutInflater inflater;
    private List<Photo> photos;
    Context context;

    public PhotoAdapter(Context context, List<Photo> photos) {
        this.photos = photos;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override

    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.item_view_card, viewGroup, false);
        return new PhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder viewHolder, int position) {
        File imgFile = new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES) + "/" +
                photos.get(position).getImageID() + ".jpg"
        );

        if (imgFile.exists()) {
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            viewHolder.ivCard.setImageBitmap(myBitmap);
        }
        //viewHolder.ivCardFront.setImageDrawable(context.getResources().getDrawable(photo.getImageID()));
    }

    @Override
    public int getItemCount() {

        return photos.size();
    }
}
