package com.example.android.memoryapp.model;
import com.example.android.memoryapp.model.Memory;
/**
 * Created by Anca on 18/03/2018.
 */

public class MemoryBuilder {
   private Memory memory;

   public MemoryBuilder(){
       memory = new Memory();
   }

   public MemoryBuilder setId(int id){
       memory.setId(id);
       return this;
   }

   public MemoryBuilder setTitle(String title){
       memory.setTitle(title);
       return this;
   }

   public MemoryBuilder setDate(String date){
       memory.setDate(date);
       return this;
   }

   public MemoryBuilder setDescription(String description){
       memory.setDescription(description);
       return this;
   }

    public MemoryBuilder setImage(byte[] image){
        memory.setImage(image);
        return this;
    }

    public Memory build(){
        return this.memory;
    }

}
