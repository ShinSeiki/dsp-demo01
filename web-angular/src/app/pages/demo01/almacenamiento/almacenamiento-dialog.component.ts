import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Almacenamiento } from 'src/app/core/model/base-model.model';
import { EntityFilterService } from 'src/app/core/service/baseentity.service';
import { BaseNavRecord } from 'src/app/core/util/base-nav-record';

@Component({
  selector: 'app-almacenamiento-dialog',
  templateUrl: './almacenamiento-dialog.component.html',
  styleUrls: ['./almacenamiento-dialog.component.scss']
})
export class AlmacenamientoDialogComponent extends BaseNavRecord<Almacenamiento, number> implements OnInit {

  formFilter: FormGroup;

  filterText: string;

  filterTAlmacenamiento: string;

  filterTRegion: string;

  showType: boolean = true;

  constructor(private serviceFilter: EntityFilterService, private formBuilder: FormBuilder, 
      public modal: NgbActiveModal
  ) { 
    super();
  }

  ngOnInit(): void {
    super.ngOnInit();

    this.formFilter = this.formBuilder.group({
      "filterTAlmacenamiento": ['', Validators.required],
      "filterTRegion": ['', Validators.required],
      "filterText": ['', Validators.required],
    });
  }

  doLoad(): void {
    this.rows = [];
    this.serviceFilter.getAlmacenamientos(0, this.filterText, this.filterTAlmacenamiento, this.filterTRegion).subscribe(
      res => {
        this.rows = res;
      }
    );
  }

}
