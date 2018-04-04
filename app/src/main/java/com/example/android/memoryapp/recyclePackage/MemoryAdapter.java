package com.example.android.memoryapp.recyclePackage;
import com.example.android.memoryapp.database.DateConversions;
import com.example.android.memoryapp.model.DayCounter;
import com.example.android.memoryapp.R;
import com.example.android.memoryapp.model.Memory;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Date;


public class MemoryAdapter extends RecyclerView.Adapter<MemoryAdapter.MemoryViewHolder> {

    final private MemoryClickListener mOnClickListener;
    private ArrayList<Memory> memories;

    public MemoryAdapter(MemoryClickListener listener) {
        mOnClickListener = listener;
    }

    /**
     *
     * This gets called when each new ViewHolder is created. This happens when the RecyclerView
     * is laid out. Enough ViewHolders will be created to fill the screen and allow for scrolling.
     *
     * @param viewGroup The ViewGroup that these ViewHolders are contained within.
     * @param viewType  If your RecyclerView has more than one type of item (which ours doesn't) you
     *                  can use this viewType integer to provide a different layout.
     * @return A new NumberViewHolder that holds the View for each list item
     */
    @Override
    public MemoryViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.list_memory;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        MemoryViewHolder viewHolder = new MemoryViewHolder(view);
        return viewHolder;
    }

    /**
     * OnBindViewHolder is called by the RecyclerView to display the data at the specified
     * position. In this method, we update the contents of the ViewHolder to display the correct
     * indices in the list for this particular position, using the "position" argument that is conveniently
     * passed into us.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(MemoryViewHolder holder, int position) {
        Memory selMemory = memories.get(position);
        DateConversions dateConversions = new DateConversions();
        Date date = dateConversions.getDateFromSQLFormat(selMemory.getDate());
        DayCounter counter = new DayCounter(date);
        long noDays =  counter.getDaysPassed();
        String daysString;
        if (noDays<0){
            daysString = Long.toString(0-noDays)+" days from today " ;
        }else{
            daysString = Long.toString(noDays)+ " days ago ";
        }

        String title= selMemory.getTitle();
        if (title.equals("")) title= "no title inserted";
        holder.titleTextView.setText(title);
        holder.daysPassedTextView.setText(daysString);
    }


    /**
     * This method simply returns the number of items to display. It is used behind the scenes
     * to help layout our Views and for animations.
     *
     * @return The number of items available
     */
    @Override
    public int getItemCount() {
        if (memories==null) return 0;
        return memories.size();
    }

    public void setMemories(ArrayList<Memory> memories) {
        this.memories = memories;
        //notifyDataSetChanged();
    }


    /**
     * Cache of the children views for a list item.
     */
    class MemoryViewHolder extends RecyclerView.ViewHolder implements OnClickListener {

        TextView titleTextView;
        TextView daysPassedTextView;

        /**
         * Constructor for our ViewHolder. Within this constructor, we get a reference to our
         * TextViews and set an onClickListener to listen for clicks. Those will be handled in the
         * onClick method below.
         * @param itemView The View that you inflated in
         */
        public MemoryViewHolder(View itemView) {
            super(itemView);

            titleTextView = (TextView) itemView.findViewById(R.id.titleTextView);
            daysPassedTextView = (TextView) itemView.findViewById(R.id.daysPassedTextView);
            //Call setOnClickListener on the View passed into the constructor (use 'this' as the OnClickListener)
            itemView.setOnClickListener(this);
        }


        // Override onClick, passing the clicked item's position (getAdapterPosition()) to mOnClickListener via its onListItemClick method
        /**
         * Called whenever a user clicks on an item in the list.
         * @param v The View that was clicked
         */
        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            Memory selMemory = memories.get(adapterPosition);
            mOnClickListener.onListItemClick(selMemory);
        }
    }
}
