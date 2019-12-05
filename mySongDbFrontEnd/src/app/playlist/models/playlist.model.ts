import {Song } from '../../Song/models/song.model';
export class Playlist {
  constructor(public id: string, public name: string, public userId: number, public songs: Song){}
}
