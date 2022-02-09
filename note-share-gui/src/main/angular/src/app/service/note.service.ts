import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Observable } from "rxjs";
import { Note, NoteShare, NoteType } from "../model/note.model";

@Injectable({
  providedIn: 'root'
})
export class NoteService {

  private readonly url = "http://localhost:8080/api/v1/notes"

  constructor(private readonly http: HttpClient) {
  }

  findAllNotes(): Observable<Note[]> {
    return this.http.get<Note[]>(this.url);
  }

  getNoteType(id: number): Observable<NoteType> {
    return this.http.get<NoteType>(`${this.url}/${id}/type`)
  }

  getNote(id: number, password?: string): Observable<Note> {
    let headers = new HttpHeaders();

    if (password) {
      headers = headers.set('Authorization', password);
    }

    return this.http.get<Note>(`${this.url}/${id}`, {headers: headers});
  }

  shareNote(note: NoteShare) {
    return this.http.post(this.url, note);
  }
}
