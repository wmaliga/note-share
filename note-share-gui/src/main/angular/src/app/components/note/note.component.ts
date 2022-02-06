import { Component, OnInit } from '@angular/core';
import { NoteService } from "../../service/note.service";
import { Observable } from "rxjs";
import { Note } from "../../model/note.model";

@Component({
  selector: 'app-note',
  templateUrl: './note.component.html',
  styleUrls: ['./note.component.css']
})
export class NoteComponent implements OnInit {

  note$: Observable<Note>;

  constructor(private readonly noteService: NoteService) {
    this.note$ = this.noteService.findNoteById(1);
  }

  ngOnInit(): void {
  }

}
