<template>
  <div class="window border border-secondary rounded p-2 mb-2" :class="{expanded: isExpanded}">
    
    <div class="top-row d-flex" @click="toggleExpand">
      <div class="window-name fw-bold pe-3">Window Name: {{window.name}}</div> 
      <div class="room-name text-muted pe-3">RoomWindow-Name: {{window.roomName}}</div>
      <div class="room-name text-muted pe-3">RoomId: {{window.roomId}}</div>

      <div class="open-status ms-4" :class="{open: isWindowOpen, closed: !isWindowOpen}">
        <template v-if="isWindowOpen">
          <span class="icon">&#x2B24;</span> Open
        </template>
        <template v-else>
          <span class="icon">&#x2716;</span> Closed
        </template>
      </div>
       
     

      <div class="expand-button ms-auto">
        {{ isExpanded ? '&#9660;' : '&#9658;' }}
      </div>
    </div>
    <template v-if="isExpanded">
      <hr/>
      <div class="details d-flex">
        <button type="button" class="btn btn-secondary me-2" @click="switchWindow">{{ isWindowOpen ? 'Close' : 'Open' }} window</button>
        <button type="button" class="btn btn-secondary me-2" @click="deletWindow(window.id)">Delete window</button>
        <button type="button" class="btn btn-secondary me-2" @click="creatWindow()">Create window</button>
      </div>
       <div>

          <input type="text" class="add-serach-input"
        v-model="newWindow.idw" placeholder="id of window">

        <input type="text" class="add-serach-input"
        v-model="newWindow.namew" placeholder="id of window">

        <input type="text" class="add-serach-input"
        v-model="newWindow.windowStatusw" placeholder="status of window">

        <input type="text" 
        v-model="newWindow.roomNamew" placeholder="room name">

        <input type="text" class="add-serach-input"
        v-model="newWindow.roomIdw" placeholder="Roomid ">
       
        <!-- @keyup.enter="creatWindow" -->

      </div>

    </template>
     
  </div>
  
</template>

<script>
import axios from 'axios';
import {API_HOST} from '../config';

export default {
  name: 'WindowsListItem',
  props: ['window'],
  
 
  data: function() {
    return {
      newWindow: {
       idw: '',
    namew: " ",
    windowStatusw: "",
    roomNamew: "",
    roomIdw: ''},
    // newName:this.newWindow.namew,
     
      isExpanded: false
    }
  }, 
  computed: {
    isWindowOpen: function() {
      return this.window.windowStatus === 'OPEN'; 
    }
  },
  methods: {
    toggleExpand() {
      this.isExpanded = !this.isExpanded;
    },
    async switchWindow() {
      let response = await axios.put(`http://localhost:8099/api/windows/${this.window.id}/switch`);
      let updatedWindow = response.data;
      this.$emit('window-updated', updatedWindow);
    },
    async deletWindow(id){
      axios.delete('http://localhost:8099/api/windows/'+id);
    }
    ,
     async creatWindow(){
      //  e.preventDefault();
      axios.post('http://localhost:8099/api/windows/',
        this.newWindow
      
      )
      .then((response) =>{
        console.log(response);
      });
    },
   
  }
}
</script>

<style lang="scss" scoped>

.open-status {
  .icon {
    position: relative;
  }

  &.open {
    color: #198754;
    .icon {
      font-size: 12px;
      top: -3px;
    }
  }

  &.closed {
    color: #dc3545;
  }
}

.window {
  .top-row {
    cursor: pointer;
  }
}
</style>
