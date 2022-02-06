import { Component, OnInit } from '@angular/core';
import { map, Observable, switchMap } from "rxjs";
import { ActivatedRoute } from "@angular/router";

import { Note } from "../../model/note.model";
import { NoteService } from "../../service/note.service";

@Component({
  selector: 'app-note',
  templateUrl: './note-details.component.html',
  styleUrls: ['./note-details.component.css']
})
export class NoteDetailsComponent implements OnInit {

  note$: Observable<Note>;

  constructor(
    private readonly route: ActivatedRoute,
    private readonly noteService: NoteService
  ) {
    this.note$ = this.route.params.pipe(
      map(params => params['id']),
      switchMap(id => this.noteService.findNoteById(id))
    );
  }

  ngOnInit(): void {
  }

}
