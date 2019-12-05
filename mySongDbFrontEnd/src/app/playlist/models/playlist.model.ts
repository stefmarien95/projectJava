import {Song } from '../../Song/models/song.model';
export class Playlist {
  constructor(public id: String, public name: String, public userId: number, public songs: Song){}
}
