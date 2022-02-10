export enum NoteType {
  PRIVATE = 'PRIVATE',
  PUBLIC = 'PUBLIC',
}

export interface Note {
  id: number;
  type: NoteType;
  title: string;
  expirationDate: string;
  data: string;
}

export interface NoteShare {
  type: NoteType;
  password?: string;
  title: string;
  expirationDate: string;
  data: string;
}