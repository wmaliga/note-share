import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { NoteService } from "../../service/note.service";
import { ToastrService } from "ngx-toastr";

@Component({
  selector: 'app-note-share',
  templateUrl: './note-share.component.html',
  styleUrls: ['./note-share.component.css']
})
export class NoteShareComponent implements OnInit {

  form: FormGroup;

  constructor(
    private readonly formBuilder: FormBuilder,
    private readonly noteService: NoteService,
    private readonly toast: ToastrService
  ) {
    this.form = this.newForm();
  }

  ngOnInit(): void {
  }

  shareNote() {
    this.noteService.shareNote(this.form.value)
      .subscribe({
        next: () => this.onShareSuccess(),
        error: error => this.onShareError(error)
      });
  }

  private onShareSuccess() {
    this.form.reset();
    this.toast.success("Note shared successfully!")
  }

  private onShareError(error: any) {
    this.toast.error("Ups! Something went wrong...")
    console.log(error);
  }

  clearForm() {
    this.form.reset();
  }

  private newForm() {
    return this.formBuilder.group({
      title: ['', Validators.required],
      data: ['', Validators.required]
    });
  }
}
