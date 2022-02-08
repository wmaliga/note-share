export enum NoteType {
  PRIVATE = 'PRIVATE',
  PUBLIC = 'PUBLIC',
}

export interface Note {
  id: number;
  type: NoteType;
  title: string;
  data: string;
}

export interface NoteShare {
  type: NoteType;
  password?: string;
  title: string;
  data: string;
}